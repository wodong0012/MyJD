package com.ye.myjd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.R;
import com.ye.myjd.bean.ROrderInfo;
import com.ye.myjd.bean.RReceiver;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.bean.RShopcar;
import com.ye.myjd.bean.SOrderParams;
import com.ye.myjd.bean.SProductParams;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.controller.ShopCarController;
import com.ye.myjd.fragment.ShopcarFragment;
import com.ye.myjd.listener.IAddOrderListener;
import com.ye.myjd.listener.IPayOnlineConfirmListener;
import com.ye.myjd.ui.pop.PayOnlineDialog;
import com.ye.myjd.ui.pop.PayWhenGetDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class SettleActivity extends BaseActivity implements View.OnClickListener, IAddOrderListener, IPayOnlineConfirmListener {

    private static final int ADD_RECEIVER_REQUEST_CODE = 0x001;
    private static final int CHOOSE_RECEIVER_REQUEST_CODE = 0x002;
    private RelativeLayout mNo_receiver_rl;
    private RelativeLayout mHas_receiver_rl;
    private TextView mName_tv;
    private TextView mPhone_tv;
    private TextView mAddress_tv;
    private ArrayList<RShopcar> mRshopcarList;
    private LinearLayout mProduct_container_ll;
    private TextView mTotal_psize_tv;
    private double mProductTotalPrice;
    private TextView mAll_price_val_tv;
    private TextView mPay_money_tv;
    private Button mPay_online_tv;
    private Button mPay_whenget_tv;
    private RReceiver mReceiver;
    private int mPayWay = -1;
    public static final String ORDER_TN_NUM = "ORDER_TN_NUM";

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.RECEIVEADDRESS_ACTION_RESULT:
                defaultReceiverAddress(msg.obj);
                break;
            case ActionConstant.ADD_ORDER_ACTION_RESUT:
                addOrderResult((RResult) msg.obj);
                break;
        }

    }

    private void addOrderResult(RResult rResult) {
        if (rResult.isSuccess()) {
            ROrderInfo rOrderInfo = JSON.parseObject(rResult.getResult(), ROrderInfo.class);

            if (rOrderInfo.getPayWay() == 1) {
                //货到付款
                PayWhenGetDialog payWhenGetDialog = new PayWhenGetDialog(this , rOrderInfo);
                payWhenGetDialog.setIAddOrderListener(this);
                payWhenGetDialog.show();
            } else if (rOrderInfo.getPayWay() == 0) {
                //在线支付
                PayOnlineDialog payOnlineDialog = new PayOnlineDialog(this, rOrderInfo);
                payOnlineDialog.setIPayOnlineConfirmListener(this);
                payOnlineDialog.show();
            }

            // TODO
            EventBus.getDefault().post(ActionConstant.SHOPCAR_ACTION);


        } else {
            tip("订单创建失败:"+rResult.getErrorMsg());
        }
    }

    @Override
    public void onAddOrder() {
        //通知对应页面刷新数据
        finish();
    }

    private void defaultReceiverAddress(Object obj) {
        mHas_receiver_rl.setVisibility(obj!=null? View.VISIBLE:View.GONE);
        mNo_receiver_rl.setVisibility(obj!=null? View.GONE:View.VISIBLE);
        if (obj != null) {
            mReceiver = (RReceiver) obj;
            mName_tv.setText(mReceiver.getReceiverName());
            mPhone_tv.setText(mReceiver.getReceiverPhone());
            mAddress_tv.setText(mReceiver.getReceiverAddress());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle);
        initController();
        initUi();
//        initData();

        mController.sendAsyncMessage(ActionConstant.RECEIVEADDRESS_ACTION, true);
    }

    private void initUi() {
        mNo_receiver_rl = findViewById(R.id.no_receiver_rl);
        mHas_receiver_rl = findViewById(R.id.has_receiver_rl);

        mName_tv = findViewById(R.id.name_tv);
        mPhone_tv = findViewById(R.id.phone_tv);
        mAddress_tv = findViewById(R.id.address_tv);

        mProduct_container_ll = findViewById(R.id.product_container_ll);
        mTotal_psize_tv = findViewById(R.id.total_psize_tv);

        mAll_price_val_tv = findViewById(R.id.all_price_val_tv);
        mPay_money_tv = findViewById(R.id.pay_money_tv);

        mPay_online_tv = findViewById(R.id.pay_online_tv);
        mPay_online_tv.setOnClickListener(this);
        mPay_whenget_tv = findViewById(R.id.pay_whenget_tv);
        mPay_whenget_tv.setOnClickListener(this);

        initProductItem();
        mTotal_psize_tv.setText("共"+ mRshopcarList.size()+"件");
        mAll_price_val_tv.setText("￥ " + mProductTotalPrice);
        mPay_money_tv.setText("￥ " + mProductTotalPrice);
    }

    @Override
    protected void initData() {
    }

    private void initProductItem() {
        //获取当前控件的的容器个数
        int childCount = mProduct_container_ll.getChildCount();
        //获取数据的长度
        int dataSize = mRshopcarList.size();

        int current = Math.min(childCount, dataSize);
        for (int i = 0; i < current; i++) {
            RShopcar rShopcar = mRshopcarList.get(i);
            LinearLayout childAt = (LinearLayout) mProduct_container_ll.getChildAt(i);
            SmartImageView smv = (SmartImageView) childAt.getChildAt(0);
            smv.setImageUrl(NetWorkConstant.BASE_URL+rShopcar.getPimageUrl());
            TextView productCount = (TextView) childAt.getChildAt(1);
            productCount.setText("x "+ rShopcar.getBuyCount());
        }
    }


    @Override
    protected void initController() {
        ArrayList<RShopcar> rShopcar = (ArrayList<RShopcar>) getIntent().getSerializableExtra(ShopcarFragment.CHECKPRODUCT);
       double price = getIntent().getDoubleExtra(ShopcarFragment.PRODUCTTOTALPRICE, 0);
        if (rShopcar.size() == 0 || price == 0) {
            finish();
        }
        mProductTotalPrice = price;
        mRshopcarList = rShopcar;
        mController = new ShopCarController(this);
        mController.setIModeChangeListener(this);
    }


    /**
     * 选择地址
     */
    public void chooseAddress(View view) {
        Intent intent = new Intent(this, ChooseReceiverActivity.class);
        startActivityForResult(intent, CHOOSE_RECEIVER_REQUEST_CODE);


    }

    /**
     * 添加地址
     */
    public void addAddress(View view) {
        Intent intent = new Intent(this, AddAddressActivity.class);
        startActivityForResult(intent, ADD_RECEIVER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_RECEIVER_REQUEST_CODE) {
            if (data != null) {
                RReceiver rReceiver = (RReceiver) data.getSerializableExtra("SAVE_RECEIVER");
                defaultReceiverAddress(rReceiver);
            }
        } else if (requestCode == CHOOSE_RECEIVER_REQUEST_CODE) {
            if (data != null) {
                RReceiver rReceiver = (RReceiver) data.getSerializableExtra(ChooseReceiverActivity.CHOOSERECEIVER);
                defaultReceiverAddress(rReceiver);
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
        }

        @Override
        public void onClick(View v) {
            mPay_online_tv.setSelected(v.getId()==R.id.pay_online_tv?true:false);
            mPay_whenget_tv.setSelected(v.getId()==R.id.pay_whenget_tv?true:false);
            switch (v.getId()) {
                case R.id.pay_online_tv:
                    //在线支付
                    mPayWay = 0;
                    break;
                case R.id.pay_whenget_tv:
                    //货到付款
                    mPayWay = 1;
                    break;
        }
    }
    //提交订单
    public void submitClick(View view) {
//        mRshopcarList 用户购买过的商品
//        userId 控制器内有
//        addrId 从mReceiver这里来
//        支付方式 判断用户选中的支付方式
//        if (mPay_online_tv.isClickable())
        if (!mPay_online_tv.isSelected() && !mPay_whenget_tv.isSelected()) {
            tip("请选择付款方式!");
            return;
        }

        SOrderParams sOrderParams = initAddOrderParams();
        // TODO 逻辑有问题，货到付款应该直接弹窗
        mController.sendAsyncMessage(ActionConstant.ADD_ORDER_ACTION, sOrderParams);
    }

    /**
        创建订单的参数
     */
    private SOrderParams initAddOrderParams() {
        //创建订单的参数对象
        SOrderParams sOrderParams = new SOrderParams();
        //创建订单内商品信息对象
        ArrayList<SProductParams> products = new ArrayList<>();
        Log.i("song", "initAddOrderParams: "+mRshopcarList);
        for (int i = 0; i < mRshopcarList.size(); i++) {
            RShopcar rShopcar = mRshopcarList.get(i);
            SProductParams sProductParams = new SProductParams();
            Log.i("song", "initAddOrderParams: "+rShopcar);
            sProductParams.setPid(rShopcar.getPid());
            sProductParams.setType(rShopcar.getPversion());
            sProductParams.setBuyCount(rShopcar.getBuyCount());
            products.add(sProductParams);
        }
        sOrderParams.setProducts(products);
        sOrderParams.setAddrId(mReceiver.getId());
        //判断在线支付方式或者货到付款是否被点击，判断在线支付是否点击 被点击返回0 没点击就一定是1
        sOrderParams.setPayWay(mPay_online_tv.isSelected()?0:1);
        //用户id在controller
        Log.i("Song", "initAddOrderParams: "+sOrderParams);
        return sOrderParams;
    }


    @Override
    public void onCancleClick() {
        //在线支付 取消按钮
        // 关闭Activity
        finish();
        //通知用户到订单列表继续支付订单
        tip("订单创建成功，请去订单列表查看订单");
    }

    @Override
    public void onSureClick(String tn) {
        //在线支付确定按钮
        Intent intent = new Intent(this, AlipayActivity.class);
        intent.putExtra(ORDER_TN_NUM, tn);
        startActivity(intent);
    }
}

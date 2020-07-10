package com.ye.myjd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.ye.myjd.R;
import com.ye.myjd.bean.RPayInfo;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.AlipayController;
import com.ye.myjd.listener.IAlipayConfirmListener;
import com.ye.myjd.ui.pop.AlipayPopWindow;

public class AlipayActivity extends BaseActivity implements IAlipayConfirmListener {

    public static final String PRODUCTORDERID = "PRODUCTORDERID";
    private TextView mPay_price_tv;
    private TextView mOrder_desc_val_tv;
    private TextView mDeal_time_val_tv;
    private TextView mDeal_no_val_tv;
    private String mTn;
    private AlipayPopWindow mAlipayPopWindow;

    @Override
    protected void userMessage(Message msg) {

        switch (msg.what) {
            case ActionConstant.GETPAYINFO_ACTION_RESULT:
                loadPayInfo(msg.obj);
                break;
            case ActionConstant.PAY_ACTION_RESULT:
                handlerPay((Long) msg.obj);
                break;
        }
    }

    private void handlerPay(long oid) {
        if (oid != 0) {
            //支付成功启动订单详情页面
            Intent intent = new Intent(this, OrderDetailsActivity.class);
            intent.putExtra(PRODUCTORDERID, oid);
            startActivity(intent);
        } else {
            tip("支付异常");
        }
    }

    private void loadPayInfo(Object obj) {
        if (obj != null) {
            RPayInfo rPayInfo = (RPayInfo) obj;
            mPay_price_tv.setText("￥ " + rPayInfo.getTotalPrice());
            mOrder_desc_val_tv.setText(rPayInfo.getOinfo());
            mDeal_time_val_tv.setText(rPayInfo.getPayTime());
            mDeal_no_val_tv.setText(rPayInfo.getTn());
        } else {
            tip("没有查询到对应的订单！");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);

        initController();
        initView();

        mController.sendAsyncMessage(ActionConstant.GETPAYINFO_ACTION, mTn);

    }

    @Override
    protected void initController() {
        Intent intent = getIntent();
        mTn = intent.getStringExtra(SettleActivity.ORDER_TN_NUM);
        if (mTn == null && mTn.equals("")) {
            tip("令牌获取失败！");
            finish();
        }
        mController = new AlipayController(this);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initView() {
        mPay_price_tv = findViewById(R.id.pay_price_tv);
        mOrder_desc_val_tv = findViewById(R.id.order_desc_val_tv);
        mDeal_time_val_tv = findViewById(R.id.deal_time_val_tv);
        mDeal_no_val_tv = findViewById(R.id.deal_no_val_tv);
    }

    public void payClick(View view) {
        //立即支付按钮
        if (mAlipayPopWindow == null) {
            mAlipayPopWindow = new AlipayPopWindow(this);
            mAlipayPopWindow.setIAlipayConfirmListener(this);
        }
        mAlipayPopWindow.show(findViewById(R.id.container));
    }

    @Override
    public void onCancleClick() {
        tip("请到订单列表中继续支付！");
        finish();
    }

    @Override
    public void onSureClick(String name, String pwd, String payPwd) {
        mController.sendAsyncMessage(ActionConstant.PAY_ACTION, mTn, name, pwd, payPwd);
    }
}

package com.ye.myjd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ye.myjd.R;
import com.ye.myjd.adapter.OrderProductsAdapter;
import com.ye.myjd.bean.ROrderDetail;
import com.ye.myjd.bean.ROrderProducts;
import com.ye.myjd.bean.RReceiver;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.AlipayController;

import java.util.List;

public class OrderDetailsActivity extends BaseActivity {

    private long mOId;
    private TextView mOrder_no_tv;
    private TextView mStatus_tv;
    private TextView mReceive_name_tv;
    private TextView mReceive_phone_tv;
    private TextView mReceive_address_tv;
    private ListView mProducts_lv;
    private TextView mTotal_price_val_tv;
    private TextView mTake_price_val_tv;
    private TextView mActual_price_tv;
    private TextView mOrder_time_tv;
    private OrderProductsAdapter mOrderProductsAdapter;

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.GETORDERDETAIL_ACTION_RESULT:
                loadOrderDetail((RResult)msg.obj);
                break;
        }
    }

    private void loadOrderDetail(RResult rResult) {
        if (rResult.isSuccess()) {
            ROrderDetail bean = JSON.parseObject(rResult.getResult(), ROrderDetail.class);

            //设置订单编号以及订单状态
            mOrder_no_tv.setText("订单编号："+bean.getOrderNum());
            initOrderStatus(mStatus_tv, bean.getStatus());

            //设置收件人信息
            RReceiver rReceiver = JSON.parseObject(bean.getAddress(), RReceiver.class);
            mReceive_name_tv.setText(rReceiver.getReceiverName());
            mReceive_phone_tv.setText(rReceiver.getReceiverPhone());
            mReceive_address_tv.setText(rReceiver.getReceiverAddress());
            //设置商品条目信息
            List<ROrderProducts> rOrderProducts = JSON.parseArray(bean.getItems(), ROrderProducts.class);
            mOrderProductsAdapter.setData(rOrderProducts);
            mOrderProductsAdapter.notifyDataSetChanged();
            //设置商品价格及运费
            mTotal_price_val_tv.setText("￥ " + bean.getTotalPrice());
            mTake_price_val_tv.setText("￥ " + bean.getFreight());
            mActual_price_tv.setText("￥ " + (bean.getTotalPrice()+bean.getFreight()));
        } else {
            tip("数据异常："+rResult.getErrorMsg());
        }

    }

    private void initOrderStatus(TextView tv, int status) {
        switch (status) {
            case -1:
                tv.setText("取消订单");
                break;
            case 0:
                tv.setText("待支付");
                break;
            case 1:
                tv.setText("待发货");
                break;
            case 2:
                tv.setText("待收货 ");
                break;
            case 3:
                tv.setText("完成交易 ");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Intent intent = getIntent();
        mOId = intent.getLongExtra(AlipayActivity.PRODUCTORDERID,0);
        if (mOId == 0) {
            //如果oid为空直接关闭页面
            finish();
        }
        initController();
        initUi();


        mController.sendAsyncMessage(ActionConstant.GETORDERDETAIL_ACTION, mOId);

    }

    private void initUi() {
        mOrder_no_tv = findViewById(R.id.order_no_tv);
        mStatus_tv = findViewById(R.id.status_tv);

        mReceive_name_tv = findViewById(R.id.receive_name_tv);
        mReceive_phone_tv = findViewById(R.id.receive_phone_tv);
        mReceive_address_tv = findViewById(R.id.receive_address_tv);

        mProducts_lv = findViewById(R.id.products_lv);
        mOrderProductsAdapter = new OrderProductsAdapter(this);
        mProducts_lv.setAdapter(mOrderProductsAdapter);

        mTotal_price_val_tv = findViewById(R.id.total_price_val_tv);
        mTake_price_val_tv = findViewById(R.id.take_price_val_tv);
        mActual_price_tv = findViewById(R.id.actual_price_tv);
        mOrder_time_tv = findViewById(R.id.order_time_tv);
    }

    @Override
    protected void initController() {


        mController = new AlipayController(this);
        mController.setIModeChangeListener(this);
    }
}

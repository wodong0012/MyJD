package com.ye.myjd.ui.pop;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ye.myjd.R;
import com.ye.myjd.bean.ROrderInfo;
import com.ye.myjd.listener.IAddOrderListener;

/**
 * @author : WoDong
 * @date : 2020/4/4 10:52
 * @desc :
 */
public class PayWhenGetDialog extends AlertDialog implements View.OnClickListener {

    private  ROrderInfo mRorderInfo;
    private TextView mOrder_no_tv;
    private TextView mTotal_price_tv;
    private TextView mFreight_tv;
    private TextView mActual_price_tv;
    private IAddOrderListener mListener;

    public PayWhenGetDialog(Context context, ROrderInfo rOrderInfo) {
        super(context, R.style.CustomDialog);
        mRorderInfo = rOrderInfo;
    }

    public void setIAddOrderListener(IAddOrderListener listener) {
        mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.build_order_pop_view);

        initView();
        initData();
    }

    private void initView() {
        mOrder_no_tv = findViewById(R.id.order_no_tv);
        mTotal_price_tv = findViewById(R.id.total_price_tv);
        mFreight_tv = findViewById(R.id.freight_tv);
        mActual_price_tv = findViewById(R.id.actual_price_tv);

        findViewById(R.id.cancal_btn).setOnClickListener(this);
        findViewById(R.id.sure_btn).setOnClickListener(this);

    }

    private void initData() {
        mOrder_no_tv.setText("订单编号："+mRorderInfo.getOrderNum());
        mTotal_price_tv.setText("总价：" + mRorderInfo.getAllPrice());
        mFreight_tv.setText("运费：" + mRorderInfo.getFreight());
        mActual_price_tv.setText("实付："+mRorderInfo.getTotalPrice());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancal_btn:
                cancel();
                break;
            case R.id.sure_btn:
                //确认按钮
                if (mListener != null) {
                    dismiss();
                    mListener.onAddOrder();
                }
                break;
        }
    }
}

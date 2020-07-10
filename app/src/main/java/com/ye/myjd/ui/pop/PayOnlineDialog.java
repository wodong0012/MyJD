package com.ye.myjd.ui.pop;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ye.myjd.R;
import com.ye.myjd.bean.ROrderInfo;
import com.ye.myjd.listener.IPayOnlineConfirmListener;

/**
 * @author : WoDong
 * @date : 2020/4/4 10:52
 * @desc :
 */
public class PayOnlineDialog extends AlertDialog implements View.OnClickListener {

    private  ROrderInfo mRorderInfo;
    private TextView mOrder_no_tv;
    private TextView mTotal_price_tv;
    private TextView mFreight_tv;
    private TextView mActual_price_tv;
    private IPayOnlineConfirmListener mListener;

    public PayOnlineDialog(Context context, ROrderInfo rOrderInfo) {
        super(context, R.style.CustomDialog);
        mRorderInfo = rOrderInfo;
    }

    public void setIPayOnlineConfirmListener(IPayOnlineConfirmListener listener) {
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
        dismiss();
        switch (v.getId()) {
            case R.id.sure_btn:
                //确认按钮
                if (mListener != null) {
                    //启动新界面
                    mListener.onSureClick(mRorderInfo.getTn());
                }
                break;
            case R.id.cancal_btn:
                if (mListener != null) {
                    // 关闭Activity
                    //通知用户到订单列表继续支付订单
                    mListener.onCancleClick();
                }
                break;
        }
    }
}

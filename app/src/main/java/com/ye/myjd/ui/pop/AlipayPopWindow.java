package com.ye.myjd.ui.pop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.ye.myjd.R;
import com.ye.myjd.listener.IAlipayConfirmListener;
import com.ye.myjd.util.TextEmpty;

/**
 * @author : WoDong
 * @date : 2020/3/25 21:55
 * @desc : 立即支付弹窗
 */
public class AlipayPopWindow extends IPopupWindowProtocal implements View.OnClickListener {


    private IAlipayConfirmListener mListener;
    private EditText mAccount_et;
    private EditText mPwd_et;
    private EditText mPay_pwd_et;

    public AlipayPopWindow(Context context) {
        super(context);
    }

    public void setIAlipayConfirmListener(IAlipayConfirmListener listener) {
        mListener = listener;
    }


    @Override
    protected void initUi() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.alipay_pop_view, null, false);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mAccount_et = contentView.findViewById(R.id.account_et);
        mPwd_et = contentView.findViewById(R.id.pwd_et);
        mPay_pwd_et = contentView.findViewById(R.id.pay_pwd_et);

        contentView.findViewById(R.id.cancel_btn).setOnClickListener(this);
        contentView.findViewById(R.id.pay_btn).setOnClickListener(this);

        //设置内容可点击
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.update();


    }

    @Override
    public void show(View anchor) {
        if (mPopupWindow != null) {
            mPopupWindow.showAtLocation(anchor, Gravity.CENTER, 0, 0);
        }
    }


    @Override
    public void onClick(View v) {
        dismiss();
        if (mListener != null)
            switch (v.getId()) {
                case R.id.cancel_btn:
                    //与activity关联
                    //提示去订单列表查看订单
                    mListener.onCancleClick();
                    break;
                case R.id.pay_btn:
                    //网络请求
                    String account = mAccount_et.getText().toString().trim();
                    String pwd = mPwd_et.getText().toString().trim();
                    String payPwd = mPay_pwd_et.getText().toString().trim();
                    if (!TextEmpty.isEmpty(account, pwd, payPwd)) {
                            mListener.onSureClick(account, pwd, payPwd);
                    } else {
                        Toast.makeText(mContext, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
    }
}

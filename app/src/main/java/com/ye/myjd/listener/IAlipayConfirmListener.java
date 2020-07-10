package com.ye.myjd.listener;

/**
 * @author : WoDong
 * @date : 2020/4/8 21:01
 * @desc : 支付密码弹窗
 */
public interface IAlipayConfirmListener {

    void onCancleClick();
    void onSureClick(String name,String pwd ,String payPwd);

}

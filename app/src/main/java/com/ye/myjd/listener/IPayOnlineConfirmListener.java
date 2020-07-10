package com.ye.myjd.listener;

/**
 * @author : WoDong
 * @date : 2020/4/8 21:01
 * @desc : 在线支付按钮监听器
 */
public interface IPayOnlineConfirmListener {

    void onCancleClick();
    void onSureClick(String tn);

}

package com.ye.myjd.cons;

/**
 * @author : WoDong
 * @date : 2020/4/5 17:37
 * @desc :
 */
public class OrderStatus {
    /**
     * 取消订单
     */
    public final static int CANCEL_ORDER = -1;
    /**
     * 待支付
     */
    public final static int WAIT_PAY = 0;
    /**
     * 代发货
     */
    public final static int WAIT_DELIVER_ORDER = 1;
    /**
     * 待收货
     */
    public final static int WAIT_RECEIVER_ORDER = 2;
    /**
     * 交易完成
     */
    public final static int COMPLETE_ORDER = 3;
}

package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/4/5 17:45
 * @desc :
 */
public class ROrderByStatus {
    private String items;//商品图片
    private String orderNum;//订单号
    private String tn;//订单令牌
    private long oid;//订单id
    private boolean paid;//是否支付
    private int status;//订单状态
    private double totalPrice;//订单总金额

    @Override
    public String toString() {
        return "ROrderByStatus{" +
                "items='" + items + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", tn='" + tn + '\'' +
                ", oid=" + oid +
                ", paid=" + paid +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

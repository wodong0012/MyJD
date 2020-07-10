package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/4/9 17:52
 * @desc :
 */
public class ROrderDetail {

    private String address;//收获地址信息 集合
    private String buyTime;//购买时间
    private String items;//商品条目信息  集合
    private String orderNum;//订单号
    private long oid;//订单id
    private double freight;//运费
    private double totalPrice;//应付总金额
    private boolean paid;//是否支付
    private int payWay;//付款方式
    private int status;//订单状态（-1 取消订单 0 待付款 1待发货 2 待收货 3已完成）,
    private String tn;//订单令牌

    @Override
    public String toString() {
        return "ROrderDetail{" +
                "address='" + address + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", items='" + items + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", oid=" + oid +
                ", freight=" + freight +
                ", totalPrice=" + totalPrice +
                ", paid=" + paid +
                ", payWay=" + payWay +
                ", status=" + status +
                ", tn='" + tn + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
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

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }
}

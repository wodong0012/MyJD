package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/4/4 10:33
 * @desc :
 */
public class ROrderInfo {

    private int errorType;//(0-成功 1-产品没库存了 2-系统失败)
    private String buyTime;//"2016-03-16 14:37:35",
    private double freight;// 运费,
    private double totalPrice;// 应付总金额,
    private double allPrice;// 商品总价格,
    private long oid;// 订单id,
    private String orderNum;// 订单号,
    private String pname;// 京东商品,
    private long payWay;// 付款方式（ 0在线支付 1货到付款）,
    private String tn;// 订单令牌

    @Override
    public String toString() {
        return "ROrderInfo{" +
                "errorType=" + errorType +
                ", buyTime='" + buyTime + '\'' +
                ", freight=" + freight +
                ", totalPrice=" + totalPrice +
                ", allPrice=" + allPrice +
                ", oid=" + oid +
                ", orderNum=" + orderNum +
                ", pname=" + pname +
                ", payWay=" + payWay +
                ", tn=" + tn +
                '}';
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
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

    public double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public long getPayWay() {
        return payWay;
    }

    public void setPayWay(long payWay) {
        this.payWay = payWay;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }
}

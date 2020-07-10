package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/4/8 21:30
 * @desc :
 */
public class RPayInfo {

    private String oinfo;//JD+订单号
    private String tn;//订单令牌
    private String pname;//京东商品
    private String payTime;//购买时间
    private String totalPrice;//总金额

    @Override
    public String toString() {
        return "RPayInfo{" +
                "oinfo='" + oinfo + '\'' +
                ", tn='" + tn + '\'' +
                ", pname='" + pname + '\'' +
                ", payTime='" + payTime + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }

    public String getOinfo() {
        return oinfo;
    }

    public void setOinfo(String oinfo) {
        this.oinfo = oinfo;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}

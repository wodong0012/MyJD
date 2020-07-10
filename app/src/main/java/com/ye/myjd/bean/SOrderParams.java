package com.ye.myjd.bean;

import java.util.ArrayList;

/**
 * @author : WoDong
 * @date : 2020/4/4 10:05
 * @desc :
 */
public class SOrderParams {
    private ArrayList<SProductParams> products;

    private long addrId; //地址id
    private long userId; //用户id
    private int payWay; //(0在线支付 1货到付款)

    @Override
    public String toString() {
        return "SOrderParams{" +
                "products=" + products +
                ", addrId=" + addrId +
                ", userId=" + userId +
                ", payWay=" + payWay +
                '}';
    }

    public ArrayList<SProductParams> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<SProductParams> products) {
        this.products = products;
    }

    public long getAddrId() {
        return addrId;
    }

    public void setAddrId(long addrId) {
        this.addrId = addrId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }
}

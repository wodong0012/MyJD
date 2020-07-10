package com.ye.myjd.bean;

import java.io.Serializable;

/**
 * @author : WoDong
 * @date : 2020/3/30 20:58
 * @desc :
 */
public class RShopcar implements Serializable {
    private long id;//购物车明细id
    private long pid;//商品id
    private long storeId;//商店id
    private int buyCount;//购买数
    private int stockCount;//库存
    private String storeName;//商店名称
    private String pversion;//商品版本
    private String pname;//商品名称
    private String pimageUrl;//商品图片路径
    private double pprice;//价格

    @Override
    public String toString() {
        return "RShopcar{" +
                "id=" + id +
                ", pid=" + pid +
                ", storeId=" + storeId +
                ", buyCount=" + buyCount +
                ", stockCount=" + stockCount +
                ", storeName='" + storeName + '\'' +
                ", pversion='" + pversion + '\'' +
                ", pname='" + pname + '\'' +
                ", pimageUrl='" + pimageUrl + '\'' +
                ", pprice=" + pprice +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPversion() {
        return pversion;
    }

    public void setPversion(String pversion) {
        this.pversion = pversion;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPimageUrl() {
        return pimageUrl;
    }

    public void setPimageUrl(String pimageUrl) {
        this.pimageUrl = pimageUrl;
    }

    public double getPprice() {
        return pprice;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }
}

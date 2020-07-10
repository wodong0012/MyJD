package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/3/20 17:22
 * @desc :
 */
public class RRecommendResult {

    /*
    "price": 商品价格,
               "name": "商品名称",
               "iconUrl": "商品图片",
               "productId": 商品id
     */
    private long productId;//商品id
    private double price;//商品价格
    private String name;//商品名称
    private String iconUrl;//商品图片

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "RRecommendResult{" +
                "producId=" + productId +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }
}

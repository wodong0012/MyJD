package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/3/28 17:33
 * @desc :
 */
public class RProductInfo {

    private long id;
    private String imgUrls;//商品图片路径
    private double price;//价格
    private boolean ifSaleOneself;//是否自营
    private String name;//商品名称
    private int stockCount;//库存
    private int commentCount;//评论数
    private String typeList;//商品版本
    private int favcomRate;//好评率


    private long recomProductId;//推荐商品id
    private String recomProduct;//推荐商品标题


    @Override
    public String toString() {
        return "RProductInfo{" +
                "id=" + id +
                ", imgUrls='" + imgUrls + '\'' +
                ", price=" + price +
                ", ifSaleOneself=" + ifSaleOneself +
                ", name=" + name +
                ", stockCount=" + stockCount +
                ", commentCount=" + commentCount +
                ", typeList='" + typeList + '\'' +
                ", favcomRate=" + favcomRate +
                ", recomProductId=" + recomProductId +
                ", recomProduct='" + recomProduct + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIfSaleOneself() {
        return ifSaleOneself;
    }

    public void setIfSaleOneself(boolean ifSaleOneself) {
        this.ifSaleOneself = ifSaleOneself;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getTypeList() {
        return typeList;
    }

    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    public int getFavcomRate() {
        return favcomRate;
    }

    public void setFavcomRate(int favcomRate) {
        this.favcomRate = favcomRate;
    }

    public long getRecomProductId() {
        return recomProductId;
    }

    public void setRecomProductId(long recomProductId) {
        this.recomProductId = recomProductId;
    }

    public String getRecomProduct() {
        return recomProduct;
    }

    public void setRecomProduct(String recomProduct) {
        this.recomProduct = recomProduct;
    }
}

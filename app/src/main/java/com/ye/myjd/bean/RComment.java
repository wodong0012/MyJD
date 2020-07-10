package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/3/29 21:14
 * @desc :
 */
public class RComment {
    private int id;//评论id
    private int rate;//星星数
    private String productType;//产品版本类型
    private String imgUrls;//["评论图片路径"]
    private String buyTime;//"2016-03-02 11:12:19"（购买时间）

    private String commentTime;//"2016-03-06 21:55:40"（评论时间）
    private int userLevel;//用户等级
    private String userName;
    private String comment;//评论内容
    private String userImg;//用户头像路径

    private int loveCount;//喜欢数
    private int subComment;//回复评论数


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(int loveCount) {
        this.loveCount = loveCount;
    }

    public int getSubComment() {
        return subComment;
    }

    public void setSubComment(int subComment) {
        this.subComment = subComment;
    }

    @Override
    public String toString() {
        return "RComment{" +
                "id=" + id +
                ", rate=" + rate +
                ", productType='" + productType + '\'' +
                ", imgUrls='" + imgUrls + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", userLevel=" + userLevel +
                ", userName='" + userName + '\'' +
                ", comment='" + comment + '\'' +
                ", userImg='" + userImg + '\'' +
                ", loveCount=" + loveCount +
                ", subComment=" + subComment +
                '}';
    }
}

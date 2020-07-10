package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/3/28 20:34
 * @desc :
 */
public class RGoodComment {

    private String imgUrls;//评论图片路径
    private String time;//评论时间
    private int rate;//星星数 最多5个
    private int type;//评论类型
    private String userName;//评论用户名
    private String comment;//评论内容


    @Override
    public String toString() {
        return "RGoodComment{" +
                "imgUrls='" + imgUrls + '\'' +
                ", time='" + time + '\'' +
                ", rate=" + rate +
                ", type=" + type +
                ", userName='" + userName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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



}

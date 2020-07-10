package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/4/4 10:06
 * @desc :
 */
public class SProductParams {
    private int buyCount;
    private String type;
    private long pid;

    @Override
    public String toString() {
        return "SProductParams{" +
                "buyCount=" + buyCount +
                ", type=" + type +
                ", pid=" + pid +
                '}';
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }
}

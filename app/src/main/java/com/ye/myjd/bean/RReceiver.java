package com.ye.myjd.bean;

import java.io.Serializable;

/**
 * @author : WoDong
 * @date : 2020/3/31 21:42
 * @desc :
 */
public class RReceiver implements Serializable {

    private long id; //地址id
    private boolean isDefault;
    private String receiverName;//接收人
    private String receiverAddress;//具体地址
    private String receiverPhone;//手机号

    @Override
    public String toString() {
        return "RReceiver{" +
                "id=" + id +
                ", isDefault=" + isDefault +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}

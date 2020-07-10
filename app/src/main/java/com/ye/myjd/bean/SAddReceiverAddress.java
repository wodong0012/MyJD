package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/4/2 21:24
 * @desc :
 */
public class SAddReceiverAddress {

   private long userId;//用户id
   private String required;//收件人
   private String phone;//电话
   private String provinceCode;//省份code
   private String cityCode;//城市code
   private String distCode;//地区code
   private String addressDetails;//详细地址
   private boolean isDefault;//是否默认

    @Override
    public String toString() {
        return "SAddReceiverAddress{" +
                "userId=" + userId +
                ", required='" + required + '\'' +
                ", phone='" + phone + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", distCode='" + distCode + '\'' +
                ", addressDetails='" + addressDetails + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}

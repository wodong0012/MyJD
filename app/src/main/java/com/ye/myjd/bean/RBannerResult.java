package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/3/18 20:52
 * @desc :
 */
public class RBannerResult {
    /*
   "success": true,
   "errorMsg": "",
   "result": [
     {
       "id": 广告id,
       "type": 跳转类型（1跳转到网页，2跳转到商品详情，3跳转到分类去）,
       "adUrl": "图片路径",
       "webUrl": "如果是跳转网页类型，则返回网页地址",
       "adKind": 广告类型（1为导航banner，2为广告banner）
      }
   ]
     */
    private long id;
    private int type;
    private String adUrl;
    private String webUrl;
    private int adKind;

    @Override
    public String toString() {
        return "RBannerResult{" +
                "id=" + id +
                ", type=" + type +
                ", adUrl='" + adUrl + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", adKind=" + adKind +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public int getAdKind() {
        return adKind;
    }

    public void setAdKind(int adKind) {
        this.adKind = adKind;
    }
}

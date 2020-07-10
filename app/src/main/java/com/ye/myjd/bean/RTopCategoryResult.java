package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/3/20 21:36
 * @desc :
 */
public class RTopCategoryResult {
    /*
    [
      {
          "id": 分类id,
          "bannerUrl": "分类图片路径",
          "name": "分类名称"
      }
  ]
     */

    private long id;
    private String bannerUrl;
    private String name;

    @Override
    public String toString() {
        return "RTopCategoryResult{" +
                "id=" + id +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

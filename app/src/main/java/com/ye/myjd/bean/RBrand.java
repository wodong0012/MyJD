package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/3/25 20:57
 * @desc :
 */
public class RBrand {
    /*
     "id": 品牌id,
            "name": "品牌名称"
     */
    private long id;
    private String name;

    @Override
    public String toString() {
        return "RBrand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

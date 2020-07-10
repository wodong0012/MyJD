package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/4/1 20:17
 * @desc :
 */
public class RArea {

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RArea{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }

    /*
      "name": "北京市",
            "code": "110000"
     */

}

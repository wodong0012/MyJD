package com.ye.myjd.cons;

/**
 * @author : WoDong
 * @date : 2020/3/15 18:04
 * @desc :
 */
public class NetWorkConstant {

    /**
     * 基础URL
     */
    //    public static final String BASE_URL = "http://10.0.2.2:8080";
    public static final String BASE_URL = "http://192.168.1.94:8080";
    /**
     * 登录URL
     */
    public static final String LOGIN_URL = BASE_URL + "/login";
    /**
     * 注册URL
     */
    public static final String REGIST_URL = BASE_URL + "/regist";

    /**
     * 获取Banner首页资源
     */
    public static final String BANNER_URL = BASE_URL + "/banner";
    /**
     * 秒杀模块url
     */
    public static final String SECKILL_URL = BASE_URL + "/seckill";

    /**
     * 猜你喜欢url
     */
    public static final String RECOMMEND_URL = BASE_URL + "/getYourFav";
    /**
     * 分类列表url
     */
    public static final String CATEGORY_URL = BASE_URL + "/category";
    /**
     * 品牌列表
     */
    public static final String BRAND_URL = BASE_URL + "/brand";
    /**
     * 搜索商品
     */
    public static final String SEND_PRODUCT_URL = BASE_URL + "/searchProduct";
    /**
     * 商品信息
     */
    public final static String PRODUCTINFO_URL = BASE_URL + "/productInfo";
    /**
     * 商品好评
     */
    public final static String PRODUCTCOMMENT_URL = BASE_URL + "/productComment";
    /**
     * 商品快照
     */
    public static final String PRODUCTDETAIL_URL = BASE_URL + "/productDetail";
    /**
     * 评论数
     */
    public static final String COMMENTCOUNT_URL = BASE_URL + "/commentCount";
    /**
     * 获取某种评论
     */
    public static final String COMMENTDETAIL_URL = BASE_URL + "/commentDetail";
    /**
     * 添加到购物车
     */
    public static final String TOSHOPCAR_URL = BASE_URL + "/toShopCar";
    /**
     * 查看购物车
     */
    public static final String SHOPCAR_URL = BASE_URL + "/shopCar";

    public static final String DELSHOPCAR = BASE_URL + "/delShopCar";

    public static final String RECEIVEADDRESS_URL = BASE_URL + "/receiveAddress";

    public static final String PROVINCE_URL = BASE_URL + "/province";

    public static final String CITY_URL = BASE_URL + "/city";

    public static final String AREA_URL = BASE_URL + "/area";

    public static final String ADDADDRESS_URL = BASE_URL + "/addAddress";

    public static final String ADD_ORDER_URL = BASE_URL + "/addOrder";

    public static final String GETORDERBYSTATUS_URL = BASE_URL + "/getOrderByStatus";

    public static final String CONFIRMORDER_URL = BASE_URL + "/confirmOrder";
    public static final String GETPAYINFO_URL = BASE_URL + "/getPayInfo";
    public static final String PAY_URL = BASE_URL + "/pay";
    public static final String GETORDERDETAIL_URL = BASE_URL + "/getOrderDetail";
}

package com.ye.myjd.listener;

/**
 * @author : WoDong
 * @date : 2020/3/31 17:43
 * @desc :
 */
public interface IShopcarCheckChanngeListener {

    void onBuyCountChanged(int num);

    void onTotalPriceChanged(double newestPrice);

}

package com.ye.myjd.listener;

import com.ye.myjd.bean.RArea;

/**
 * @author : WoDong
 * @date : 2020/4/1 21:06
 * @desc :
 */
public interface IOnChooseAddressCheangeListener {
    /**
     * 当选择的地址发生改变
     */
    void onChooseAddressChanged(RArea province , RArea city, RArea dist);
}

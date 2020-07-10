package com.ye.myjd.listener;

/**
 * @author : WoDong
 * @date : 2020/3/26 20:32
 * @desc :
 */
public interface IProductStorChangeListener {

    /**
     * 1-综合
     */
     int ALL_SORT = 1;
    /**
     * 2-新品
     */
    int NEW_SORT = 2;
    /**
     * 3-评价
     */
    int COMMENT_SORT = 3;

    void onStorChanged(int action);
}

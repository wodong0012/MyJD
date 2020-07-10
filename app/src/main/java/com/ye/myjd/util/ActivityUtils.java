package com.ye.myjd.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ye.myjd.activity.BaseActivity;

/**
 * @author : WoDong
 * @date : 2020/3/15 17:44
 * @desc :  页面跳转工具类
 */
public class ActivityUtils {
    /**
     * 页面跳转方法
     * @param context 需要的上下文
     * @param zClass 需要跳转的页面
     * @param isOffActivity 是否需要关闭当前页面
     */
    public static void startActivity(Context context, Class<? extends BaseActivity> zClass, boolean isOffActivity) {
        Intent intent = new Intent(context, zClass);
        context.startActivity(intent);
        if (isOffActivity) {
            ((Activity) context).finish();
        }
    }
}

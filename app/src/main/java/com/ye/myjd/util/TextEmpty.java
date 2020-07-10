package com.ye.myjd.util;

import android.text.TextUtils;

/**
 * @author : WoDong
 * @date : 2020/4/8 21:54
 * @desc :
 */
public class TextEmpty {

    public static boolean isEmpty(String... value) {
        for (String str: value) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

}

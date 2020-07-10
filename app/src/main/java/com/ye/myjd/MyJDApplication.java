package com.ye.myjd;

import android.app.Application;

import com.ye.myjd.bean.RLoginResult;

/**
 * @author : WoDong
 * @date : 2020/3/17 21:55
 * @desc :
 */
public class MyJDApplication extends Application {

    private RLoginResult mRloginResult;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public RLoginResult getRloginResult() {
        return mRloginResult;
    }

    public void setRloginResult(RLoginResult rloginResult) {
        mRloginResult = rloginResult;
    }
}

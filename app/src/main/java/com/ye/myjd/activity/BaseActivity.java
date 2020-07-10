package com.ye.myjd.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ye.myjd.controller.BaseController;
import com.ye.myjd.listener.IModeChangeListener;

/**
 * @author : WoDong
 * @date : 2020/3/15 16:47
 * @desc :
 */
public abstract class BaseActivity extends AppCompatActivity implements IModeChangeListener {
    protected BaseController mController;
    protected Handler mHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                userMessage(msg);
            }
        };

    protected  void userMessage(Message msg){
        // default Empty implements
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initController();
        initView();
        initData();
        initAnimation();
        initEvent();
    }

    protected  void initController(){
        // default Empty implements
    }


    protected void initView() {

    }

    protected  void initData(){

    }

    /**
     * 初始化动画，子类自由实现
     */
    protected void initAnimation(){
    }

    /**
     * 初始化事件子类自由实现
     */
    protected void initEvent(){}

    /**
     *  多空判断
     * @param value 需要判断的字符串
     * @return 有空的字符串就返回false
     */
    protected boolean isEmpty(String... value) {
        for (String str: value) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    protected void tip(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onModeChange(int action, Object...values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
}

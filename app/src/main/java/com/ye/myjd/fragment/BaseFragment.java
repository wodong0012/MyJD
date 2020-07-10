package com.ye.myjd.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ye.myjd.controller.BaseController;
import com.ye.myjd.listener.IModeChangeListener;

public abstract class BaseFragment extends Fragment implements View.OnClickListener, IModeChangeListener {

//    protected FragmentActivity mActivity;
    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            userMessage(msg);
        }
    };
    protected BaseController mController;
    protected FragmentActivity mActivity;


    protected  void userMessage(Message msg){
        // default Empty implementn
    }

    protected  void initController(){
        // default Empty implementn
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
//        initController();

    }

    protected  void initData(){

    }

    protected void initView() {

    }

    protected  void initEvent(){

    }

    @Override
    public void onModeChange(int action, Object...values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onClick(View v) {

    }
    protected void tip(String string) {
        Toast.makeText(mActivity, string, Toast.LENGTH_SHORT).show();
    }

}

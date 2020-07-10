package com.ye.myjd.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.ye.myjd.MyJDApplication;
import com.ye.myjd.R;
import com.ye.myjd.activity.MyOrderActivity;
import com.ye.myjd.activity.LoginActivity;
import com.ye.myjd.bean.RLoginResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.HomeController;
import com.ye.myjd.util.ActivityUtils;


public class MyJDFragment extends BaseFragment {


    private ImageView mUser_icon_iv;
    private TextView mWait_pay_tv;
    private TextView mWait_receive_tv;
    private Button mLogout_btn;
    private MyJDApplication mApplication;
    private FragmentActivity mActivity;
    private TextView user_name_tv;
    private TextView mUser_level_tv;
    private LinearLayout mAll_order;

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.CLEAR_USER_ACTION_RESULT:
                ActivityUtils.startActivity(getContext(), LoginActivity.class, true);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myjd, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        initController();
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        mUser_icon_iv = mActivity.findViewById(R.id.user_icon_iv);
        mWait_pay_tv = mActivity.findViewById(R.id.wait_pay_tv);
        mWait_receive_tv = mActivity.findViewById(R.id.wait_receive_tv);
        mLogout_btn = mActivity.findViewById(R.id.logout_btn);
        user_name_tv = mActivity.findViewById(R.id.user_name_tv);
        mUser_level_tv = mActivity.findViewById(R.id.user_level_tv);

        mActivity.findViewById(R.id.all_order).setOnClickListener(this);

    }

    @Override
    protected void initData() {
        mApplication = ((MyJDApplication) mActivity.getApplication());
        RLoginResult rloginResult = mApplication.getRloginResult();
        user_name_tv.setText(rloginResult.getUserName());
        mWait_pay_tv.setText(rloginResult.getWaitPayCount() + "");
        mWait_receive_tv.setText(rloginResult.getWaitReceiveCount() + "");

        setUserLevel(rloginResult);
    }

    private void setUserLevel(RLoginResult rloginResult) {
        //用户等级（1注册会员2铜牌会员3银牌会员4金牌会员5钻石会员）
        switch (rloginResult.getUserLevel()) {
            case 1:
                mUser_level_tv.setText("注册会员");
                break;
            case 2:
                mUser_level_tv.setText("铜牌会员");
                break;
            case 3:
                mUser_level_tv.setText("银牌会员");
                break;
            case 4:
                mUser_level_tv.setText("金牌会员");
                break;
            case 5:
                mUser_level_tv.setText("钻石会员");
                break;
            default:
                mUser_level_tv.setText("注册会员");
                break;
        }
    }

    @Override
    protected void initEvent() {
        mLogout_btn.setOnClickListener(this);

    }

    @Override
    protected void initController() {
        mController = new HomeController(getActivity());
        mController.setIModeChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_btn:
                //用户点击退出登录
                //1.清空application中的对象
                mApplication.setRloginResult(null);
                //2.清空数据库
                mController.sendAsyncMessage(ActionConstant.CLEAR_USER_ACTION, 0);
                break;
            case R.id.all_order:
//                ActivityUtils.startActivity(mActivity, MyOrderActivity.class, false);
                Intent intent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}

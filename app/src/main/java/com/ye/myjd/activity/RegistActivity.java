package com.ye.myjd.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.ye.myjd.R;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.UserController;

/**
 * @author : WoDong
 * @date : 2020/3/15 21:06
 * @desc :
 */
public class RegistActivity extends BaseActivity {

    private EditText mUsername_et;
    private EditText mPwd_et;
    private EditText mSurepwd_et;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initController();
        initView();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_regist);
        mUsername_et = findViewById(R.id.username_et);
        mPwd_et = findViewById(R.id.pwd_et);
        mSurepwd_et = findViewById(R.id.surepwd_et);

    }

    public void registClick(View view) {
        //注册按钮
        String userName = mUsername_et.getText().toString().trim();
        String pwd = mPwd_et.getText().toString().trim();
        mController.sendAsyncMessage(ActionConstant.REGIST_ACTION, userName,pwd);
    }

    @Override
    protected void initController() {
        mController = new UserController(this);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.REGIST_ACTION_RESULT:
                RResult rResult = (RResult) msg.obj;
                if (!rResult.isSuccess()) {
                    tip(rResult.getErrorMsg());
                    return;
                }
                finish();
                break;
        }
    }


}

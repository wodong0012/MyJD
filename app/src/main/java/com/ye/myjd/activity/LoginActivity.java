package com.ye.myjd.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.ye.myjd.MyJDApplication;
import com.ye.myjd.R;
import com.ye.myjd.bean.RLoginResult;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.UserController;
import com.ye.myjd.db.UserDao.UserInfo;
import com.ye.myjd.util.ActivityUtils;

/**
 * @author : WoDong
 * @date : 2020/3/15 17:30
 * @desc :
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mName_et;
    private EditText mPwd_et;
    private Button mLogin_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initController();
//        initView();
//        initEvent();
    }

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.LOGIN_ACTION_RESULT:
                loginResult(msg);
                break;
            case ActionConstant.SAVE_USER_RESULT:
                saveUser((Boolean) msg.obj);
                break;
            case ActionConstant.GET_USER_ACTION_RESULT:

                handlerGetUser(msg.obj);
                break;
        }
    }

    private void handlerGetUser(Object object) {
        if (object != null) {
            UserInfo userInfo = (UserInfo) object;
            mName_et.setText(userInfo.name);
            mPwd_et.setText(userInfo.pwd);
        }
    }

    private void saveUser(boolean save) {
        if (save) {
            ActivityUtils.startActivity(this, MainActivity.class, true);
        } else {
            tip("数据错误");
        }
    }

    @Override
    protected void initEvent() {
        mLogin_btn.setOnClickListener(this);
    }

    private void loginResult(Message msg) {
        RResult rResult = (RResult) msg.obj;
        if (!rResult.isSuccess()) {
            tip(rResult.getErrorMsg());
            return;
        }
        //保存用户信息到Application
        String json = rResult.getResult();
        RLoginResult rLoginResult = JSONObject.parseObject(json, RLoginResult.class);
        ((MyJDApplication)getApplication()).setRloginResult(rLoginResult);


        //界面初始化之后加载用户是否登录过
        String name = mName_et.getText().toString().trim();
        String pwd = mPwd_et.getText().toString().trim();
        mController.sendAsyncMessage(ActionConstant.SAVE_USER, name, pwd);
    }

    @Override
    protected void initView() {

        setContentView(R.layout.activity_login);

        mName_et = findViewById(R.id.name_et);
        mPwd_et = findViewById(R.id.pwd_et);
        mLogin_btn = findViewById(R.id.login_btn);

        //启动页面时就查询数据库是否有登录记录
        mController.sendAsyncMessage(ActionConstant.GET_USER_ACTION, 0);
    }

    @Override
    protected void initController() {
        mController = new UserController(this);
        mController.setIModeChangeListener(this);
    }

    /**
     * 注册用户
     */
    public void registClick(View view) {
        ActivityUtils.startActivity(this, RegistActivity.class, false);
    }

    /**
     * 重置密码
     */
    public void resetPwdClick(View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                login();
                break;
        }
    }

    private void login() {
        String name = mName_et.getText().toString().trim();
        String pwd = mPwd_et.getText().toString().trim();
        if (isEmpty(name, pwd)) {
            tip("账号或密码为空");
            return;
        }
        mController.sendAsyncMessage(ActionConstant.LOGIN_ACTION, name, pwd);
    }

}

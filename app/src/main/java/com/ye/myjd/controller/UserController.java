package com.ye.myjd.controller;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.ye.myjd.MyJDApplication;
import com.ye.myjd.bean.RLoginResult;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.db.UserDao;
import com.ye.myjd.db.UserDao.UserInfo;
import com.ye.myjd.util.NetWorkUtil;

import java.util.HashMap;

/**
 * @author : WoDong
 * @date : 2020/3/15 17:55
 * @desc :
 */
public class UserController extends BaseController {

    private UserDao mUserDao = new UserDao(mContext);
    protected long mUserId ;

    public UserController(Context context) {
        super(context);
        Activity activity = (Activity) context;
        MyJDApplication myJDApplication = (MyJDApplication) activity.getApplication();
        RLoginResult rloginResult = myJDApplication.getRloginResult();
        if (rloginResult != null) {
            mUserId = rloginResult.getId();
        }
    }


    @Override
    protected void handlerMessage(int action, Object[] values) {
        switch (action) {
            case ActionConstant.LOGIN_ACTION:
                // doGet doPost 请求
                RResult loginResult = loginOrRegist(NetWorkConstant.LOGIN_URL, (String) values[0], (String) values[1]);
                mIModeChangeListener.onModeChange(ActionConstant.LOGIN_ACTION_RESULT, loginResult);
                break;
            case ActionConstant.REGIST_ACTION:
                RResult registResult = loginOrRegist(NetWorkConstant.REGIST_URL, (String) values[0], (String) values[1]);
                mIModeChangeListener.onModeChange(ActionConstant.REGIST_ACTION_RESULT, registResult);
                break;
            case ActionConstant.SAVE_USER:
                clearUser();
                boolean b = saveUser((String) values[0], (String) values[1]);
                mIModeChangeListener.onModeChange(ActionConstant.SAVE_USER_RESULT, b);
                break;
            case ActionConstant.GET_USER_ACTION:
                UserInfo userInfo = aquireUser();
                mIModeChangeListener.onModeChange(ActionConstant.GET_USER_ACTION_RESULT,userInfo);
                break;
            case ActionConstant.CLEAR_USER_ACTION:
                clearUser();
                mIModeChangeListener.onModeChange(ActionConstant.CLEAR_USER_ACTION_RESULT,0);
                break;

        }
    }

    private UserInfo aquireUser() {
        UserInfo userInfo = mUserDao.aquireUser();
        return userInfo;
    }

    private void clearUser(){
            mUserDao.clearUser();
        }

        private boolean saveUser(String name, String pwd) {
//            try {
//                name = AESUtils.encrypt(name);
//                pwd = AESUtils.encrypt(pwd);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            return mUserDao.saveUser(name, pwd);
    }


    private RResult loginOrRegist(String loginUrl, String name, String pwd) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", name);
        params.put("pwd", pwd);



        String json = NetWorkUtil.doPost(loginUrl, params);
        if (json != null) {
            RResult rResult = JSONObject.parseObject(json, RResult.class);
            return rResult;
        }
        return new RResult();
    }
}

package com.ye.myjd.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ye.myjd.bean.RPayInfo;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.util.NetWorkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author : WoDong
 * @date : 2020/4/8 21:24
 * @desc :
 */
public class AlipayController extends UserController {
    public AlipayController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object[] values) {
        switch (action) {
            case ActionConstant.GETPAYINFO_ACTION:
                mIModeChangeListener.onModeChange(ActionConstant.GETPAYINFO_ACTION_RESULT,
                        loadPayInfo((String) values[0])
                );
                break;
            case ActionConstant.PAY_ACTION:
                mIModeChangeListener.onModeChange(ActionConstant.PAY_ACTION_RESULT,
                        pay((String) values[0], (String) values[1], (String) values[2], (String) values[3]));
                break;
            case ActionConstant.GETORDERDETAIL_ACTION:
                mIModeChangeListener.onModeChange(ActionConstant.GETORDERDETAIL_ACTION_RESULT,
                        loadOrderDetail((long) values[0]));
                break;
        }
    }

    private RResult loadOrderDetail(long oId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", mUserId + "");
        params.put("id", oId+"");
        String json = NetWorkUtil.doPost(NetWorkConstant.GETORDERDETAIL_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        return rResult;
    }

    private long pay(String tn, String name, String pwd, String payPwd) {
        HashMap<String, String> params = new HashMap<>();
        params.put("tn", tn);
        params.put("userId", mUserId + "");
        params.put("account", name);
        params.put("apwd", pwd);
        params.put("ppwd", payPwd);
        String json = NetWorkUtil.doPost(NetWorkConstant.PAY_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            try {
                JSONObject jsonObject = new JSONObject(rResult.getResult());
                long oid = jsonObject.getLong("oid");
                return oid;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private RPayInfo loadPayInfo(String tn) {
        HashMap<String, String> params = new HashMap<>();
        params.put("tn", tn);
        params.put("userId", mUserId + "");
        String json = NetWorkUtil.doPost(NetWorkConstant.GETPAYINFO_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            RPayInfo rPayInfo = JSON.parseObject(rResult.getResult(), RPayInfo.class);
            return rPayInfo;
        }
        return null;
    }
}

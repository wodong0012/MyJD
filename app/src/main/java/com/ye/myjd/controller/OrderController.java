package com.ye.myjd.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.util.NetWorkUtil;

import java.util.HashMap;

/**
 * @author : WoDong
 * @date : 2020/4/5 17:35
 * @desc :
 */
public class OrderController extends UserController {
    public OrderController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object[] values) {
        switch (action) {
            case ActionConstant.WAIT_PAY_ACTION:
                mIModeChangeListener.onModeChange(ActionConstant.WAIT_PAY_ACTION_RESULT,
                        loadWaitPayOrder((int) values[0]));
                break;
            case ActionConstant.WAIT_RECEIVER_ACTION:
                mIModeChangeListener.onModeChange(ActionConstant.WAIT_RECEIVER_ACTION_RESULT,
                        loadWaitPayOrder((int) values[0]));
                break;
            case ActionConstant.COMPLETEDORDER_ACTION:
                mIModeChangeListener.onModeChange(ActionConstant.COMPLETEDORDER_ACTION_RESULT,
                        loadWaitPayOrder((int) values[0]));
                break;
            case ActionConstant.CONFIRMORDER_ACTION:
                mIModeChangeListener.onModeChange(ActionConstant.CONFIRMORDER_ACTION_RESULT,
                        confirmOrder((long) values[0]));
                break;
        }
    }

    private RResult confirmOrder(long oId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", mUserId + "");
        params.put("oid", oId + "");
        String json = NetWorkUtil.doPost(NetWorkConstant.CONFIRMORDER_URL, params);
        return JSON.parseObject(json, RResult.class);
    }

    private RResult loadWaitPayOrder(int status) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", mUserId + "");
        params.put("status", status + "");
        String json = NetWorkUtil.doPost(NetWorkConstant.GETORDERBYSTATUS_URL, params);
        return JSON.parseObject(json, RResult.class);
    }
}

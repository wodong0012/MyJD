package com.ye.volleydemo.controller;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.ye.volleydemo.cons.IdiyMessage;
import com.ye.volleydemo.cons.NetworkConst;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : WoDong
 * @date : 2020/3/15 17:55
 * @desc :
 */
public class UserController extends BaseController {

    protected long mUserId;

    public UserController(Context context) {
        super(context);
    }

    @Override
    protected StringRequest initRequest(int action, Response.Listener<String> listener, final Object... values) {
        switch (action) {
            case IdiyMessage.LOGIN_ACTION:
                return  initLoginRequest(listener, (String) values[0],(String) values[1]);
        }
        return null;
    }

    private StringRequest initLoginRequest(final Response.Listener<String> listener, final String name, final String pwd) {
        return new StringRequest(Request.Method.POST, NetworkConst.LOGIN_URL, listener, null){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username",name );
                params.put("pwd", pwd);
                return params;
            }
        };
    }


}

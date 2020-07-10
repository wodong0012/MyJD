package com.ye.volleydemo.controller;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ye.volleydemo.listener.IModeChangeListener;

/**
 * @author : WoDong
 * @date : 2020/3/15 17:56
 * @desc :
 */
public abstract class BaseController {
    protected Context mContext;
    protected IModeChangeListener mIModeChangeListener;
    protected static RequestQueue mQueue;

    public BaseController(Context context) {
        mContext = context;
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(mContext);
        }
    }

    public void setIModeChangeListener(IModeChangeListener listener) {
        this.mIModeChangeListener = listener;
    }

    public void sendAsyncMessage(final int aciton, final Object... values) {
        Response.Listener<String> listener = initResponseListener(aciton);
        StringRequest stringRequest = initRequest(aciton, listener, values);
        mQueue.add(stringRequest);
    }

    protected abstract StringRequest initRequest(int action, Response.Listener<String> listener, Object... values);


    protected Response.Listener<String> initResponseListener(final int action) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mIModeChangeListener.onModeChanged(action, response);
            }
        };
    }


}

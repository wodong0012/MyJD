package com.ye.myjd.controller;

import android.content.Context;

import com.ye.myjd.listener.IModeChangeListener;

/**
 * @author : WoDong
 * @date : 2020/3/15 17:56
 * @desc :
 */
public abstract class BaseController {
    protected Context mContext;
    protected IModeChangeListener mIModeChangeListener;

    public BaseController(Context context) {
        mContext = context;
    }

    public void setIModeChangeListener(IModeChangeListener listener) {
        this.mIModeChangeListener = listener;
    }

    public void sendAsyncMessage(final int aciton ,final Object... values) {
        new Thread() {
            @Override
            public void run() {
                handlerMessage(aciton, values);
            }
        }.start();
    }

    protected abstract void handlerMessage(int action, Object[] values);

}

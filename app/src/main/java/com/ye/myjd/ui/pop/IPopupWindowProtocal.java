package com.ye.myjd.ui.pop;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author : WoDong
 * @date : 2020/3/25 22:02
 * @desc :
 */
public abstract class IPopupWindowProtocal {

    protected Context mContext;
    protected PopupWindow mPopupWindow;

    public IPopupWindowProtocal(Context context) {
        mContext = context;
        initController();
        initUi();
    }

    protected void initController() {

    }

    protected abstract void initUi();

    public  abstract void  show(View anchor);

    public void dismiss(){
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }



}
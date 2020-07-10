package com.ye.myjd.ui.pop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ye.myjd.R;
import com.ye.myjd.listener.IProductStorChangeListener;

/**
 * @author : WoDong
 * @date : 2020/3/25 21:55
 * @desc : 商品搜索弹出框
 */
public class ProductSortPopuWindow extends IPopupWindowProtocal implements View.OnClickListener {


    private IProductStorChangeListener mListener;

    public ProductSortPopuWindow(Context context) {
        super(context);
    }

    public void setIPopupWindowProtocal(IProductStorChangeListener listener) {
        mListener = listener;
    }


    @Override
    protected void initUi() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.product_sort_pop_view, null,false);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        contentView.findViewById(R.id.all_sort).setOnClickListener(this);
        contentView.findViewById(R.id.new_sort).setOnClickListener(this);
        contentView.findViewById(R.id.comment_sort).setOnClickListener(this);

        //设置内容可点击
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.update();



    }

    @Override
    public void  show(View anchor){
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor, 0, 0);
        }
    }


    @Override
    public void onClick(View v) {
        if (mListener != null)
        switch (v.getId()) {
            case R.id.all_sort:
                mListener.onStorChanged(IProductStorChangeListener.ALL_SORT);
                break;
            case R.id.new_sort:
                mListener.onStorChanged(IProductStorChangeListener.NEW_SORT);
                break;
            case R.id.comment_sort:
                mListener.onStorChanged(IProductStorChangeListener.COMMENT_SORT);
                break;
        }
        dismiss();
    }
}

package com.ye.myjd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/3/19 20:45
 * @desc :
 */
public abstract class JDBaseAdapter<T> extends BaseAdapter {

    protected List<T> mDatas;
    protected Context mActivity;
    protected final LayoutInflater mInflater;

    public void setData(List<T> datas) {
        this.mDatas = datas;
    }

    public JDBaseAdapter(Context c) {
        mActivity = c;
        mInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return mDatas!=null?mDatas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}

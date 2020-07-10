package com.ye.myjd.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ye.myjd.R;
import com.ye.myjd.bean.RBrand;

/**
 * @author : WoDong
 * @date : 2020/3/25 20:46
 * @desc :
 */
public class ProductTypeListAdapter extends JDBaseAdapter<String> {

    public ProductTypeListAdapter(Activity c) {
        super(c);
}

    public int mCurrentTabPosition = -1;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView btn = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.brand_lv_item_tv, parent, false);
            btn = convertView.findViewById(R.id.brand_tv);
            convertView.setTag(btn);
        } else {
            btn = (TextView) convertView.getTag();
        }

        btn.setSelected(mCurrentTabPosition == position);

        String type = mDatas.get(position);
        btn.setText(type);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return mDatas!=null?mDatas.get(position):"";
    }
}

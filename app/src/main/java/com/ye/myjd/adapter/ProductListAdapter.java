package com.ye.myjd.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.R;
import com.ye.myjd.bean.RProductList;
import com.ye.myjd.cons.NetWorkConstant;

/**
 * @author : WoDong
 * @date : 2020/3/27 18:44
 * @desc :
 */
public class ProductListAdapter extends JDBaseAdapter<RProductList> {
    public ProductListAdapter(Activity c) {
        super(c);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductListHolder productListHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.product_lv_item, parent, false);
            productListHolder = new ProductListHolder(convertView);
            convertView.setTag(productListHolder);
        } else {
            productListHolder = (ProductListHolder) convertView.getTag();
        }
        productListHolder.setData(position);
        return convertView;
    }

    class ProductListHolder {
        private SmartImageView product_iv;
        private TextView name_tv;
        private TextView commrate_tv;
        private TextView price_tv;

        public ProductListHolder(View convertView) {
            product_iv = convertView.findViewById(R.id.product_iv);
            name_tv = convertView.findViewById(R.id.name_tv);
            commrate_tv = convertView.findViewById(R.id.commrate_tv);
            price_tv = convertView.findViewById(R.id.price_tv);
        }

        public void setData(int position) {
            RProductList rProductList = mDatas.get(position);
            product_iv.setImageUrl(NetWorkConstant.BASE_URL+rProductList.getIconUrl());
            name_tv.setText(rProductList.getName());
            commrate_tv.setText(rProductList.getCommentCount()+"");
            price_tv.setText(rProductList.getPrice()+"");
        }
    }

    @Override
    public long getItemId(int position) {
        return mDatas != null?mDatas.get(position).getId():0;
    }
}

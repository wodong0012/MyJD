package com.ye.myjd.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.R;
import com.ye.myjd.bean.ROrderProducts;
import com.ye.myjd.cons.NetWorkConstant;

/**
 * @author : WoDong
 * @date : 2020/3/30 20:52
 * @desc :
 */
public class OrderProductsAdapter extends JDBaseAdapter<ROrderProducts> {


    public OrderProductsAdapter(Activity c) {
        super(c);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShopcarHolder shopcarHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.order_details_products_item, parent, false);
            shopcarHolder = new ShopcarHolder(convertView);
            convertView.setTag(shopcarHolder);
        } else {
            shopcarHolder = (ShopcarHolder) convertView.getTag();
        }

        shopcarHolder.setData(position);

        return convertView;
    }


    class ShopcarHolder {

        private SmartImageView p_icon_iv;
        private TextView p_name_iv;
        private TextView p_buycount_iv;
        private TextView p_price_iv;

        public ShopcarHolder(View view) {
            p_icon_iv = view.findViewById(R.id.p_icon_iv);
            p_name_iv = view.findViewById(R.id.p_name_iv);
            p_buycount_iv = view.findViewById(R.id.p_buycount_iv);
            p_price_iv = view.findViewById(R.id.p_price_iv);
        }

        public void setData(int position) {
            ROrderProducts rOrderProducts = mDatas.get(position);
            p_icon_iv.setImageUrl(NetWorkConstant.BASE_URL+rOrderProducts.getPiconUrl());
            p_name_iv.setText(rOrderProducts.getPname());
            p_buycount_iv.setText("X "+rOrderProducts.getBuyCount());
            p_price_iv.setText("￥ "+rOrderProducts.getAmount());
        }

    }
}
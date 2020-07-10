package com.ye.myjd.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.R;
import com.ye.myjd.bean.RRecommendResult;
import com.ye.myjd.cons.NetWorkConstant;

/**
 * @author : WoDong
 * @date : 2020/3/20 17:18
 * @desc :
 */
public class RecommendGridViewAdapter extends JDBaseAdapter<RRecommendResult> {
    public RecommendGridViewAdapter(Activity c) {
        super(c);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecommendGridViewHolder recommendGridViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.recommend_gv_item, parent,false);
            recommendGridViewHolder = new RecommendGridViewHolder(convertView);
            convertView.setTag(recommendGridViewHolder);
        } else {
            recommendGridViewHolder = (RecommendGridViewHolder) convertView.getTag();
        }
        recommendGridViewHolder.setData(position);
        return convertView;
    }

    class RecommendGridViewHolder {
        SmartImageView image_iv;
        TextView name_tv;
        TextView price_tv;

        public RecommendGridViewHolder(View view) {
            image_iv = view.findViewById(R.id.image_iv);
            name_tv =  view.findViewById(R.id.name_tv);
            price_tv = view.findViewById(R.id.price_tv);
        }


        public void setData(int position) {
            RRecommendResult rRecommendResult = mDatas.get(position);
            name_tv.setText(rRecommendResult.getName());
            price_tv.setText(rRecommendResult.getPrice() + "");
            image_iv.setImageUrl(NetWorkConstant.BASE_URL+rRecommendResult.getIconUrl());
        }
    }

    @Override
    public long getItemId(int position) {
        return mDatas!=null?mDatas.get(position).getProductId():0;
    }
}

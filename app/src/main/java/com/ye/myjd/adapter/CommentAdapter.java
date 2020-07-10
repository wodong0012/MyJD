package com.ye.myjd.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.R;
import com.ye.myjd.bean.RComment;
import com.ye.myjd.bean.RGoodComment;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.ui.Rating_bar;

import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/3/29 21:45
 * @desc :
 */
public class CommentAdapter extends JDBaseAdapter<RComment> {
    public CommentAdapter(Activity c) {
        super(c);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentHolder commentHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.comment_item_view, parent, false);
            commentHolder = new CommentHolder(convertView);
            convertView.setTag(commentHolder);
        } else {
            commentHolder = (CommentHolder) convertView.getTag();
        }

        commentHolder.setData(position);

        return convertView;
    }

    class CommentHolder {
        private SmartImageView icon_iv;
        private TextView name_tv;
        private TextView time_tv;
        private TextView buyversion_tv;
        private TextView content_tv;
        private TextView lovecount_tv;
        private TextView buytime_tv;
        private TextView subcomment_tv;
        private LinearLayout iamges_container;
        private Rating_bar rating_bar;

        public CommentHolder(View convertView) {
            icon_iv = convertView.findViewById(R.id.icon_iv);
            name_tv = convertView.findViewById(R.id.name_tv);
            time_tv = convertView.findViewById(R.id.time_tv);
            content_tv = convertView.findViewById(R.id.content_tv);
            rating_bar = convertView.findViewById(R.id.rating_bar);
            buyversion_tv = convertView.findViewById(R.id.buyversion_tv);
            lovecount_tv = convertView.findViewById(R.id.lovecount_tv);
            subcomment_tv = convertView.findViewById(R.id.subcomment_tv);
            iamges_container = convertView.findViewById(R.id.iamges_container);
             buytime_tv = convertView.findViewById(R.id.buytime_tv);
        }

        public void setData(int position) {
            RComment rComment = mDatas.get(position);
            icon_iv.setImageUrl(NetWorkConstant.BASE_URL + rComment.getUserImg());
            name_tv.setText(rComment.getUserName());
            time_tv.setText(rComment.getCommentTime());
            content_tv.setText(rComment.getComment());
            rating_bar.setRatingCount(rComment.getRate());
            lovecount_tv.setText("喜欢(" + rComment.getLoveCount() + ")");
            subcomment_tv.setText("回复" + rComment.getSubComment() + ")");
            buytime_tv.setText("购买日期："+rComment.getBuyTime());
            buyversion_tv.setText("型号"+rComment.getProductType());
            initCommentImage(rComment.getImgUrls());
        }

        private void initCommentImage(String imgUrls) {
            List<String> urls = JSON.parseArray(imgUrls, String.class);
            //1.知道容器 iamges_container
            //2.知道里面要显示多少内容
            //如果是3 显示3 个
            //如果是5 显示4 个
            int dataSize = urls.size();
            int childCount = iamges_container.getChildCount();
            int real = Math.min(dataSize, childCount);

            //清空老的数据
            for (int a = 0; a < childCount; a++) {
                SmartImageView smiv = (SmartImageView) iamges_container.getChildAt(a);
                smiv.setImageDrawable(new BitmapDrawable());
            }

            for (int a = 0; a < real; a++) {
                SmartImageView smiv = (SmartImageView) iamges_container.getChildAt(a);
                smiv.setImageUrl(NetWorkConstant.BASE_URL+urls.get(a));
            }
            iamges_container.setVisibility(dataSize > 0 ? View.VISIBLE : View.GONE);
        }
    }


}

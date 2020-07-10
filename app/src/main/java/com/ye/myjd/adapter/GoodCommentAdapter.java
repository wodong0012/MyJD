package com.ye.myjd.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.R;
import com.ye.myjd.bean.RGoodComment;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.ui.Rating_bar;

import java.util.List;

import static com.ye.myjd.R.drawable.start_normal;
import static com.ye.myjd.R.drawable.start_selected;

/**
 * @author : WoDong
 * @date : 2020/3/28 20:33
 * @desc :
 */
public class GoodCommentAdapter extends JDBaseAdapter<RGoodComment> {
    public GoodCommentAdapter(Activity c) {
        super(c);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoodCommentHolder goodCommentHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.recommend_comment_item_view, parent, false);
            //创建holder，并找到控件
            goodCommentHolder = new GoodCommentHolder(convertView);
            convertView.setTag(goodCommentHolder);
        } else {
            goodCommentHolder = (GoodCommentHolder) convertView.getTag();
        }
        goodCommentHolder.setData(position);
        return convertView;
    }

    class GoodCommentHolder {

        private Rating_bar rating_bar;
        private LinearLayout iamges_container;
        private TextView name_tv;
        private TextView content_tv;

        public GoodCommentHolder(View convertView) {
            rating_bar = convertView.findViewById(R.id.rating_bar);
            iamges_container = convertView.findViewById(R.id.iamges_container);
            name_tv = convertView.findViewById(R.id.name_tv);
            content_tv = convertView.findViewById(R.id.content_tv);
        }

        public void setData(int position) {
            RGoodComment rGoodComment = mDatas.get(position);

            name_tv.setText(rGoodComment.getUserName());

            rating_bar.setRatingCount(rGoodComment.getRate());

            content_tv.setText(rGoodComment.getComment());
            initCommentImage(rGoodComment);
        }

        private void initCommentImage(RGoodComment rGoodComment) {
            List<String> urls = JSON.parseArray(rGoodComment.getImgUrls(), String.class);
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

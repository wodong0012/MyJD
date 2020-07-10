package com.ye.myjd.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.bean.ROrderByStatus;
import com.ye.myjd.cons.NetWorkConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/4/5 21:41
 * @desc :
 */
public abstract class OrderBaseAdapter  extends JDBaseAdapter<ROrderByStatus> implements View.OnClickListener{

    public OrderBaseAdapter(Context c) {
        super(c);
    }

    protected void initProductItems(LinearLayout products, String data){
        List<String> urls = JSON.parseArray(data, String.class);

        //        urls.stream().reduce(s -> NetWorkConstant.BASE_URL+ s).collect(Collectors.toList());

        ArrayList<String> imgUrl = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            imgUrl.add(NetWorkConstant.BASE_URL + urls.get(i));
        }

        int childCount = products.getChildCount();
        int dataSize = imgUrl.size();
        int minSize = Math.min(childCount, dataSize);
        for (int i = 0; i < minSize; i++) {
            ImageView siv = (ImageView) products.getChildAt(i);
            //            siv.setImageDrawable(new BitmapDrawable());
            siv.setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i < minSize; i++) {
            SmartImageView siv = (SmartImageView) products.getChildAt(i);
            siv.setImageUrl(imgUrl.get(i));
            siv.setVisibility(View.VISIBLE);
        }

    }

    protected void initOrderStatus(int status, TextView tv) {
        switch (status) {
            case -1:
                tv.setText("取消订单");
                break;
            case 0:
                tv.setText("待支付");
                break;
            case 1:
                tv.setText("待发货");
                break;
            case 2:
                tv.setText("待收货 ");
                break;
            case 3:
                tv.setText("完成交易 ");
                break;
        }
    }

    @Override
    public long getItemId(int position) {
        return mDatas != null? mDatas.get(position).getOid():0;
    }

    @Override
    public void onClick(View v) {

    }
}

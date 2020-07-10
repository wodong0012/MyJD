package com.ye.myjd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.cons.NetWorkConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/3/28 17:57
 * @desc :
 */
public class ADPagerAdapter extends PagerAdapter {

    private final Context mContent;
    private List<String> mUrls;
    private ArrayList<SmartImageView> mSmartImageViews ;

    public ADPagerAdapter(Context context){
        mContent = context;
    }

    @Override
    public int getCount() {
        return mSmartImageViews != null? mSmartImageViews.size():0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SmartImageView smartImageView = mSmartImageViews.get(position);
        container.addView(smartImageView);
        return smartImageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        SmartImageView smartImageView = mSmartImageViews.get(position);
        container.removeView(smartImageView);
    }

    public void setData( List<String> datas) {
        mUrls = datas;
        mSmartImageViews = new ArrayList<>();
        for (String url : mUrls) {
            String imgUrl = NetWorkConstant.BASE_URL + url;
            SmartImageView smartImageView = new SmartImageView(mContent);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            smartImageView.setLayoutParams(params);
            smartImageView.setImageUrl(imgUrl);
            mSmartImageViews.add(smartImageView);
        }
    }
}

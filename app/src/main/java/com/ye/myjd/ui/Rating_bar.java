package com.ye.myjd.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.ye.myjd.R;

/**
 * @author : WoDong
 * @date : 2020/3/28 21:26
 * @desc :
 */
public class Rating_bar extends LinearLayout {
    public Rating_bar(Context context) {
        super(context,null);
    }

    public Rating_bar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置好评的星星数
     * @param count
     */
    public void setRatingCount(int count) {
        for (int a = 0; a < getChildCount(); a++) {
            ImageView imageView = (ImageView) getChildAt(a);
            imageView.setImageResource(R.drawable.start_normal);
        }
        for (int a = 0; a < count; a++) {
            ImageView imageView = (ImageView) getChildAt(a);
            imageView.setImageResource(R.drawable.start_selected);
        }

    }

}

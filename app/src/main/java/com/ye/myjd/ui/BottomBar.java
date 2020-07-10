package com.ye.myjd.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.os.MessageQueue;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ye.myjd.R;
import com.ye.myjd.activity.MainActivity;
import com.ye.myjd.listener.IBottomBarClickListener;

/**
 * @author : WoDong
 * @date : 2020/3/16 17:30
 * @desc :
 */
public class BottomBar extends LinearLayout implements View.OnClickListener {

    private ImageView mHomeIv;
    private TextView mHomeTv;
    private ImageView mCategoryIv;
    private TextView mCategoryTv;
    private ImageView mShopcarIv;
    private TextView mShopcarTv;
    private ImageView mMyJDIv;
    private TextView mMyJDTv;
    private int mCurrenTabId;
    private IBottomBarClickListener mListener;

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initEvent();
    }

    private void initEvent() {

    }

    /**
     * 当布局渲染完成过后的回调
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        findViewById(R.id.frag_main_ll).setOnClickListener(this);
        findViewById(R.id.frag_category_ll).setOnClickListener(this);
        findViewById(R.id.frag_shopcar_ll).setOnClickListener(this);
        findViewById(R.id.frag_mine_ll).setOnClickListener(this);

        mHomeIv = findViewById(R.id.frag_main_iv);
        mHomeTv = findViewById(R.id.frag_main);
        mCategoryIv = findViewById(R.id.frag_category_iv);
        mCategoryTv = findViewById(R.id.frag_category);
        mShopcarIv = findViewById(R.id.frag_shopcar_iv);
        mShopcarTv = findViewById(R.id.frag_shopcar);
        mMyJDIv = findViewById(R.id.frag_mine_iv);
        mMyJDTv = findViewById(R.id.frag_mine);

        setFontType(mHomeTv);
        setFontType(mCategoryTv);
        setFontType(mShopcarTv);
        setFontType(mMyJDTv);

        //模拟点击
        findViewById(R.id.frag_main_ll).performClick();


    }

    private void setFontType(TextView v) {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"font.ttf");
        v.setTypeface(tf);
    }

    @Override
    public void onClick(View v) {
        if (mCurrenTabId==v.getId()) {
            return;
        }
        // 1.让所有的控件设置为false
        mHomeIv.setSelected(v.getId() == R.id.frag_main_ll);
        mHomeTv.setSelected(v.getId() == R.id.frag_main_ll);
        mCategoryIv.setSelected(v.getId() == R.id.frag_category_ll);
        mCategoryTv.setSelected(v.getId() == R.id.frag_category_ll);
        mShopcarIv.setSelected(v.getId() == R.id.frag_shopcar_ll);
        mShopcarTv.setSelected(v.getId() == R.id.frag_shopcar_ll);
        mMyJDIv.setSelected(v.getId() == R.id.frag_mine_ll);
        mMyJDTv.setSelected(v.getId() == R.id.frag_mine_ll);
        if (mListener!=null) {
            mListener.onItemClick(v.getId());
            mCurrenTabId=v.getId();
        }
    }

    public void setIBottomBarClickListener(IBottomBarClickListener listener) {
        this.mListener = listener;
    }
}

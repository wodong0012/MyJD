package com.ye.myjd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ye.myjd.R;
import com.ye.myjd.bean.RProductInfo;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.ProductController;
import com.ye.myjd.fragment.ProductCommentFragment;
import com.ye.myjd.fragment.ProductDetailsFragment;
import com.ye.myjd.fragment.ProductIntroduceFragment;

import java.util.ArrayList;

/**
 * @author : WoDong
 * @date : 2020/3/27 19:13
 * @desc :
 */
public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    public long mProductId;
    public int mBuyCount = 1;
    public String mPversion = "";
    private View mIntroduce_view;
    private View mDetails_view;
    private View mComment_view;
    private ViewPager mContainer_vp;
    private ContainerAdapter mContainerAdapter;

    @Override
    protected void userMessage(Message msg) {

        RResult rResult = (RResult) msg.obj;
        if (rResult.isSuccess()) {
            tip("添加购物车成功");
            finish();
        } else {
            tip("添加购物车失败");
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initController();
        initUI();
    }


    @Override
    protected void initController() {
        mController = new ProductController(this);
        mController.setIModeChangeListener(this);
    }

    protected void initUI() {
        findViewById(R.id.introduce_ll).setOnClickListener(this);
        findViewById(R.id.details_ll).setOnClickListener(this);
        findViewById(R.id.comment_ll).setOnClickListener(this);


        mIntroduce_view = findViewById(R.id.introduce_view);
        mDetails_view = findViewById(R.id.details_view);
        mComment_view = findViewById(R.id.comment_view);


        mContainer_vp = findViewById(R.id.container_vp);
        mContainerAdapter = new ContainerAdapter(getSupportFragmentManager());
        mContainer_vp.setAdapter(mContainerAdapter);
        mContainer_vp.setOnPageChangeListener(this);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        defalutIndicator();
        switch (position) {
            case 0:
                mContainer_vp.setCurrentItem(0);
                break;
            case 1:
                mDetails_view.setVisibility(View.VISIBLE);
                break;
            case 2:
                mComment_view.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 添加购物车按钮
     */
    public void add2ShopCar(View view) {
        /*
        userId: required (integer)
        用户id
        productId: required (integer)
        商品id
        buyCount: required (integer)
        购买数量
        pversion : required(String)
                */
        if (mBuyCount != 0&&"".equals(mPversion)) {
            return;
        }
        mController.sendAsyncMessage(ActionConstant.TOSHOPCAR_ACTION, mProductId,mBuyCount,mPversion);
    }

    class ContainerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments = new ArrayList<>();

        public ContainerAdapter(FragmentManager fm) {
            super(fm);
            mFragments.add(new ProductIntroduceFragment());
            mFragments.add(new ProductDetailsFragment());
            mFragments.add(new ProductCommentFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mProductId = intent.getLongExtra(ProductListActivity.TODETAILS_KEY, 0);
        if (mProductId == 0) {
            tip("数据异常");
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        defalutIndicator();
        switch (v.getId()) {
            case R.id.introduce_ll://商品
                mIntroduce_view.setVisibility(View.VISIBLE);
                mContainer_vp.setCurrentItem(0);
                break;
            case R.id.details_ll://详情
                mDetails_view.setVisibility(View.VISIBLE);
                mContainer_vp.setCurrentItem(1);
                break;
            case R.id.comment_ll://评价
                mComment_view.setVisibility(View.VISIBLE);
                mContainer_vp.setCurrentItem(2);
                break;
        }
    }

    private void defalutIndicator() {
        mIntroduce_view.setVisibility(View.INVISIBLE);
        mDetails_view.setVisibility(View.INVISIBLE);
        mComment_view.setVisibility(View.INVISIBLE);
    }
}

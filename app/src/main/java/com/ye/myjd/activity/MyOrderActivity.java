package com.ye.myjd.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ye.myjd.R;
import com.ye.myjd.fragment.AllOrderFragment;
import com.ye.myjd.fragment.BaseFragment;
import com.ye.myjd.fragment.CompletedOrderFragment;
import com.ye.myjd.fragment.WaitPayFragment;
import com.ye.myjd.fragment.WaitReceiveFragment;

import java.util.ArrayList;

/**
 * @author : WoDong
 * @date : 2020/4/4 20:11
 * @desc :
 */
public class MyOrderActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private static final String TAG = "song";
    private View mAll_order_view;
    private View mWait_pay_view;
    private View mWait_receive_view;
    private View mWait_sure_view;
    private ViewPager mOrder_vp;
    private OrderGvAdapter mOrderGvAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        initView();
        initUi();
    }

    private void initUi() {
        findViewById(R.id.all_order_ll).setOnClickListener(this);
        findViewById(R.id.wait_pay_ll).setOnClickListener(this);
        findViewById(R.id.wait_receive_ll).setOnClickListener(this);
        findViewById(R.id.wait_sure_ll).setOnClickListener(this);

        mOrder_vp = findViewById(R.id.order_vp);
        mOrder_vp.addOnPageChangeListener(this);
        mOrderGvAdapter = new OrderGvAdapter(getSupportFragmentManager());
        mOrder_vp.setAdapter(mOrderGvAdapter);

        mAll_order_view = findViewById(R.id.all_order_view);
        mWait_pay_view = findViewById(R.id.wait_pay_view);
        mWait_receive_view = findViewById(R.id.wait_receive_view);
        mWait_sure_view = findViewById(R.id.wait_sure_view);

        //第一个模拟点击
        findViewById(R.id.all_order_ll).performClick();
    }

    public void defaultIndicator() {
        mAll_order_view.setVisibility(View.INVISIBLE);
        mWait_pay_view.setVisibility(View.INVISIBLE);
        mWait_receive_view.setVisibility(View.INVISIBLE);
        mWait_sure_view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        defaultIndicator();

        switch (v.getId()) {
            case R.id.all_order_ll:
                Log.i(TAG, "all_order_ll: ");
                mOrder_vp.setCurrentItem(0);
                mAll_order_view.setVisibility(View.VISIBLE);

                break;
            case R.id.wait_pay_ll:
                mWait_pay_view.setVisibility(View.VISIBLE);
                mOrder_vp.setCurrentItem(1);

                Log.i(TAG, "wait_pay_ll: ");

                break;
            case R.id.wait_receive_ll:
                mOrder_vp.setCurrentItem(2);
                mWait_receive_view.setVisibility(View.VISIBLE);

                break;
            case R.id.wait_sure_ll:
                mOrder_vp.setCurrentItem(3);

                mWait_sure_view.setVisibility(View.VISIBLE);

                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        defaultIndicator();
        switch (position) {
            case 0:
                mAll_order_view.setVisibility(View.VISIBLE);
                mOrder_vp.setCurrentItem(0);
                break;
            case 1:
                mWait_pay_view.setVisibility(View.VISIBLE);
                mOrder_vp.setCurrentItem(1);

                break;
            case 2:
                mWait_receive_view.setVisibility(View.VISIBLE);
                mOrder_vp.setCurrentItem(2);

                break;
            case 3:
                mWait_sure_view.setVisibility(View.VISIBLE);
                mOrder_vp.setCurrentItem(3);

                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class OrderGvAdapter extends FragmentPagerAdapter{

        private ArrayList<BaseFragment> mFragments;

        public OrderGvAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
            mFragments.add(new AllOrderFragment());
            mFragments.add(new WaitPayFragment());
            mFragments.add(new WaitReceiveFragment());
            mFragments.add(new CompletedOrderFragment());
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

}

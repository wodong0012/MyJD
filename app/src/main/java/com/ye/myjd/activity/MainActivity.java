package com.ye.myjd.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ye.myjd.R;
import com.ye.myjd.fragment.CategoryFragment;
import com.ye.myjd.fragment.HomeFragment;
import com.ye.myjd.fragment.MyJDFragment;
import com.ye.myjd.fragment.ShopcarFragment;
import com.ye.myjd.listener.IBottomBarClickListener;
import com.ye.myjd.ui.BottomBar;

public class MainActivity extends BaseActivity implements IBottomBarClickListener {
    private BottomBar mBottomBar;
    private FragmentManager mFm;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initController();
//        initView();
//    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        //获取Fragment管理器
        mFm = getSupportFragmentManager();

        // 1.初始化底部栏
        mBottomBar =  findViewById(R.id.bottom_bar);
        //设置监听器
        mBottomBar.setIBottomBarClickListener(this);
        // 初始化顶部的Fragment

        //1.开启事务
        FragmentTransaction transaction = mFm.beginTransaction();
        //2.设置HomeFragemt为默认显示
        transaction.replace(R.id.top_bar, new HomeFragment());
        //3.提交事务
        transaction.commit();
    }

    @Override
    public void onItemClick(int action) {
        //1.开启事务
        FragmentTransaction transaction = mFm.beginTransaction();
        switch (action) {
            case R.id.frag_main_ll:
                //2.获取被点击的条目，设置对应的Fragment
                transaction.replace(R.id.top_bar, new HomeFragment());
                break;
            case R.id.frag_category_ll:
                transaction.replace(R.id.top_bar, new CategoryFragment());
                break;
            case R.id.frag_shopcar_ll:
                transaction.replace(R.id.top_bar, new ShopcarFragment());
                break;
            case R.id.frag_mine_ll:
                transaction.replace(R.id.top_bar, new MyJDFragment());
                break;
        }
        //提交事务
        transaction.commit();
    }
}

package com.ye.myjd.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ye.myjd.R;
import com.ye.myjd.activity.SettleActivity;
import com.ye.myjd.adapter.ShopcarLvAdapter;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.bean.RShopcar;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.ShopCarController;
import com.ye.myjd.listener.IShopcarCheckChanngeListener;
import com.ye.myjd.listener.IShopcarDeleteListener;
import com.ye.myjd.ui.FlexiListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ShopcarFragment extends BaseFragment implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener, IShopcarCheckChanngeListener, IShopcarDeleteListener {

    public static final String CHECKPRODUCT = "CHECKPRODUCT";
    public static String PRODUCTTOTALPRICE = "PRODUCTTOTALPRICE";
    private FlexiListView mShopcar_lv;
    private ShopcarLvAdapter mShopcarLvAdapter;
    private CheckBox mAll_cbx;
    private TextView mSettle_tv;
    private TextView mAll_money_tv;
    private double mTotalPrice;

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.SHOPCAR_ACTION_RESULT:
                loadShopcar((List<RShopcar>) msg.obj);
                break;
            case ActionConstant.DELETE_SHOPCARITEM_ACTION_RESULT:
                deleteShopCarItem((RResult) msg.obj);
                break;
        }
    }

    private void deleteShopCarItem(RResult rResult) {
        if (rResult.isSuccess()) {
            mController.sendAsyncMessage(ActionConstant.SHOPCAR_ACTION);
        } else {
            tip("删除失败："+rResult.getErrorMsg());
        }
    }

    private void loadShopcar(List<RShopcar> datas) {
        mShopcarLvAdapter.setData(datas);
        mShopcarLvAdapter.notifyDataSetChanged();
        mSettle_tv.setText("去结算(0)");

        mAll_money_tv.setText("总额: ￥ 0" );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  refreshLV(Integer action) {
        switch (action) {
            case ActionConstant.SHOPCAR_ACTION:
                mController.sendAsyncMessage(ActionConstant.SHOPCAR_ACTION);
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopcar, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initView();

        mController.sendAsyncMessage(ActionConstant.SHOPCAR_ACTION);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initController() {
        mController = new ShopCarController(mActivity);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initView() {
        mShopcar_lv = mActivity.findViewById(R.id.shopcar_lv);
        mShopcarLvAdapter = new ShopcarLvAdapter(mActivity);
        mShopcarLvAdapter.setIShopcarCheckChanngeListener(this);
        mShopcarLvAdapter.setIShopcarDeleteListener(this);
        mShopcar_lv.setAdapter(mShopcarLvAdapter);
        mShopcar_lv.setOnItemClickListener(this);

        mSettle_tv = mActivity.findViewById(R.id.settle_tv);
        mSettle_tv.setOnClickListener(this);
        mAll_money_tv = mActivity.findViewById(R.id.all_money_tv);

        mAll_cbx = mActivity.findViewById(R.id.all_cbx);
        mAll_cbx.setOnCheckedChangeListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settle_tv:
                if (!mShopcarLvAdapter.ifItemChecked()) {
                    tip("请选中要购买的商品！");
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(getContext(), SettleActivity.class);
                ArrayList<RShopcar> checkItems = mShopcarLvAdapter.getCheckItems();
                Log.i("Song", "onClick: "+checkItems);
                intent.putExtra(PRODUCTTOTALPRICE, mTotalPrice);
                intent.putExtra(CHECKPRODUCT, checkItems);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mShopcarLvAdapter.setCheck(position);
        mShopcarLvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mShopcarLvAdapter.setAllCheck(isChecked);
    }

    @Override
    public void onBuyCountChanged(int num) {
        mSettle_tv.setText("去结算(" + num + ")");
    }

    @Override
    public void onTotalPriceChanged(double newestPrice) {
        mTotalPrice = newestPrice;
        mAll_money_tv.setText("总额: ￥ " + newestPrice);
    }

    @Override
    public void deleteProduct(long shopCarId) {
        mController.sendAsyncMessage(ActionConstant.DELETE_SHOPCARITEM_ACTION, shopCarId);
    }
}

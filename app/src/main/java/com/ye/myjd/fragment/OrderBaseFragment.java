package com.ye.myjd.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import androidx.fragment.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.ye.me.maxwin.view.XListView;
import com.ye.myjd.activity.AlipayActivity;
import com.ye.myjd.activity.OrderDetailsActivity;
import com.ye.myjd.adapter.OrderBaseAdapter;
import com.ye.myjd.bean.ROrderByStatus;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.controller.OrderController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/4/5 21:31
 * @desc :
 */
public abstract class OrderBaseFragment extends BaseFragment implements XListView.IXListViewListener, AdapterView.OnItemClickListener {

    protected XListView mOrder_lv;
    protected OrderBaseAdapter mAdapter;

    protected void loadOrderStatus(RResult rResult) {
        if (rResult.isSuccess()) {
            List<ROrderByStatus> rOrderByStatuses = JSON.parseArray(rResult.getResult(), ROrderByStatus.class);
            mAdapter.setData(rOrderByStatuses);
            mAdapter.notifyDataSetChanged();
            String date = getCurrentDate();
            mOrder_lv.setRefreshTime(date);
            //结束刷新
            mOrder_lv.stopRefresh();

        } else {
            tip("数据异常：" + rResult.getErrorMsg());
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm-ss");
        return simpleDateFormat.format(new Date());
    }

    protected void initOrderAdapter(int resId, Class<? extends OrderBaseAdapter> classz) {
        try {
            FragmentActivity activity = getActivity();
            System.out.println(activity);

            mAdapter =  classz.getDeclaredConstructor(Context.class).newInstance(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mOrder_lv = mActivity.findViewById(resId);
        mOrder_lv.setOnItemClickListener(this);
        mOrder_lv.setXListViewListener(this);
        mOrder_lv.setPullRefreshEnable(true);
        mOrder_lv.setPullLoadEnable(false);
        //通过子类来回调IOC
        //        mAdapter = new WaitPayAdapter(mActivity);
        mOrder_lv.setAdapter(mAdapter);
    }

    @Override
    protected void initController() {
        mController = new OrderController(mActivity);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initEvent() {

    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long oId = mAdapter.getItemId(position- 1);
        Activity activity = getActivity();
        Intent intent = new Intent(activity, OrderDetailsActivity.class);
        intent.putExtra(AlipayActivity.PRODUCTORDERID, oId );
        startActivity(intent);
    }
}

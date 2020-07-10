package com.ye.myjd.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ye.myjd.R;
import com.ye.myjd.adapter.CompletedOrderAdapter;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.OrderStatus;


/**
 * 已完成页
 * */
public class CompletedOrderFragment extends OrderBaseFragment  {

	@Override
	protected void userMessage(Message msg) {
		switch (msg.what) {
			case ActionConstant.COMPLETEDORDER_ACTION_RESULT:
				loadOrderStatus((RResult) msg.obj);
				break;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_completed_order, container,false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initController();
		initView();
		initEvent();

		mController.sendAsyncMessage(ActionConstant.COMPLETEDORDER_ACTION, OrderStatus.COMPLETE_ORDER);
	}

	@Override
	protected void initView() {
		initOrderAdapter(R.id.completed_order_lv, CompletedOrderAdapter.class);
	}

	@Override
	public void onRefresh() {
		mController.sendAsyncMessage(ActionConstant.COMPLETEDORDER_ACTION, OrderStatus.COMPLETE_ORDER);
	}

}

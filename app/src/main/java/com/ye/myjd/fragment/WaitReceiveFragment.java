package com.ye.myjd.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ye.myjd.R;
import com.ye.myjd.adapter.WaitReceiverAdapter;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.OrderStatus;
import com.ye.myjd.listener.IOrderConfirmReceiverListener;


/**
 * 待收货
 * */
public class WaitReceiveFragment extends OrderBaseFragment implements IOrderConfirmReceiverListener {

	@Override
	protected void userMessage(Message msg) {
		switch (msg.what) {
			case ActionConstant.WAIT_RECEIVER_ACTION_RESULT:
				loadOrderStatus((RResult) msg.obj);
				break;
			case ActionConstant.CONFIRMORDER_ACTION_RESULT:
				confirmOrder((RResult) msg.obj);
				break;
		}
	}

	private void confirmOrder(RResult rResult) {
		if (rResult.isSuccess()) {
			tip("收货成功！");
		} else {
			tip("确认失败："+rResult.getErrorMsg());
		}
		mController.sendAsyncMessage(ActionConstant.WAIT_RECEIVER_ACTION, OrderStatus.WAIT_RECEIVER_ORDER);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_wait_receive, container,false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initController();
		initView();
		initEvent();

		mController.sendAsyncMessage(ActionConstant.WAIT_RECEIVER_ACTION, OrderStatus.WAIT_RECEIVER_ORDER);
	}

	@Override
	protected void initView() {
		initOrderAdapter(R.id.receiver_order_lv, WaitReceiverAdapter.class);
		((WaitReceiverAdapter)mAdapter).setIOrderConfirmReceiverListener(this);
	}

	@Override
	public void onRefresh() {
		mController.sendAsyncMessage(ActionConstant.WAIT_RECEIVER_ACTION, OrderStatus.WAIT_RECEIVER_ORDER);
	}

	@Override
	public void onOrderReceiver(long oId) {
		mController.sendAsyncMessage(ActionConstant.CONFIRMORDER_ACTION,oId);
	}
}

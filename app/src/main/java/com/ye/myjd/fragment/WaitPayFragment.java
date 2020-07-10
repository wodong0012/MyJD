package com.ye.myjd.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.ye.myjd.R;
import com.ye.myjd.adapter.WaitPayAdapter;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.OrderStatus;


/**
 * 待支付
 * */
public class WaitPayFragment extends OrderBaseFragment implements AdapterView.OnItemClickListener {

	@Override
	protected void userMessage(Message msg) {
		switch (msg.what) {
			case ActionConstant.WAIT_PAY_ACTION_RESULT:
				loadOrderStatus((RResult) msg.obj);
				break;
		}
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_wait_pay, container,false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initController();
		initView();
		initEvent();
		mController.sendAsyncMessage(ActionConstant.WAIT_PAY_ACTION, OrderStatus.WAIT_PAY);
	}



	@Override
	protected void initView() {
		initOrderAdapter(R.id.all_order_lv,WaitPayAdapter.class);

//		((WaitPayAdapter)mAdapter).setIOnOrderPayClickListener(this);
	}




	@Override
	public void onRefresh() {
		mController.sendAsyncMessage(ActionConstant.WAIT_PAY_ACTION, OrderStatus.WAIT_PAY);
	}


}

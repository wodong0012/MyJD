package com.ye.volleydemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.ye.volleydemo.controller.BaseController;
import com.ye.volleydemo.listener.IModeChangeListener;


public abstract class BaseActivity extends FragmentActivity implements
		IModeChangeListener {

	protected BaseController mController;
	
	protected void initData() {
		// default Empty implementn
	}

	protected void initController() {
		// default Empty implementn
	}

	protected abstract void initUI();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	public void tip(String tipStr) {
		Toast.makeText(this, tipStr, 0).show();
	}

	@Override
	public void onModeChanged(int action,String jsonStr) {
		//有的子界面需要网络请求 可能需要回调   有的界面可能就不需要回调了
		// default Empty implementn
	}

	protected boolean ifValueWasEmpty(String... values) {
		for (String value : values) {
			if (TextUtils.isEmpty(value)) {
				return true;
			}
		}
		return false;
	}
}

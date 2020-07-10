package com.ye.volleydemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ye.volleydemo.R;
import com.ye.volleydemo.cons.IdiyMessage;
import com.ye.volleydemo.controller.UserController;

public class LoginActivity extends BaseActivity {

	private EditText mNameEt;
	private EditText mPwdEt;

	@Override
	public void onModeChanged(int action, String jsonStr) {
		if (action== IdiyMessage.LOGIN_ACTION) {
			tip("登录的信息:"+jsonStr);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initController();
		initUI();
	}

	public void loginClick(View v) {
		String name = mNameEt.getText().toString();
		String pwd = mPwdEt.getText().toString();
		if (ifValueWasEmpty(name, pwd)) {
			tip("请输入账号密码");
			return;
		}
		// 发送一个网络请求 去请求网络数据
		mController.sendAsyncMessage(IdiyMessage.LOGIN_ACTION, name, pwd);
	}

	@Override
	protected void initController() {
		mController = new UserController(this);
		mController.setIModeChangeListener(this);
	}

	@Override
	protected void initUI() {
		mNameEt =  findViewById(R.id.name_et);
		mPwdEt = findViewById(R.id.pwd_et);
	}

}

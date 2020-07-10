package com.ye.myjd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.ye.myjd.R;
import com.ye.myjd.bean.RArea;
import com.ye.myjd.bean.RReceiver;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.bean.SAddReceiverAddress;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.ShopCarController;
import com.ye.myjd.listener.IOnChooseAddressCheangeListener;
import com.ye.myjd.ui.Rating_bar;
import com.ye.myjd.ui.pop.ChooseAreaPopWindow;

/**
 * @author : WoDong
 * @date : 2020/3/31 21:54
 * @desc :
 */
public class AddAddressActivity extends BaseActivity implements IOnChooseAddressCheangeListener {

    private EditText mName_et;
    private EditText mPhone_et;
    private TextView mChoose_province_tv;
    private TextView mAddress_details_et;
    private CheckBox mDefault_cbx;
    private ChooseAreaPopWindow mChooseAreaPopWindow;
    private RArea mProvince;
    private RArea mCity;
    private RArea mDist;

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.ADD_ADDRESS_ACTION_RESULT:
                handlerSaveAddress((RResult) msg.obj);
                break;
        }

    }

    private void handlerSaveAddress(RResult rResult) {
        if (rResult.isSuccess()) {
            tip("保存成功！");
            RReceiver rReceiver = JSON.parseObject(rResult.getResult(), RReceiver.class);
            Intent intent = new Intent();
            intent.putExtra("SAVE_RECEIVER", rReceiver);
            setResult(0, intent);
            finish();
        } else {
            tip("保存失败：" + rResult.getErrorMsg());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initController();
        initView();
    }

    @Override
    protected void initController() {
        mController = new ShopCarController(this);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initView() {
        mName_et = findViewById(R.id.name_et);
        mPhone_et = findViewById(R.id.phone_et);
        mChoose_province_tv = findViewById(R.id.choose_province_tv);

        mAddress_details_et = findViewById(R.id.address_details_et);
        mDefault_cbx = findViewById(R.id.default_cbx);
    }

    public void saveAddress(View view) {
        System.out.println("点击事件");
        //保存按钮
        String name = mName_et.getText().toString().trim();
        String phone = mPhone_et.getText().toString().trim();
        String detailsAddress = mAddress_details_et.getText().toString().trim();

        if (isEmpty(name, phone, detailsAddress) && isObjectEmpty(mProvince, mCity, mDist)) {
            tip("请将信息补充完整！");
            return;
        }

        boolean isDefault = mDefault_cbx.isChecked();

        SAddReceiverAddress sAddReceiverAddress = new SAddReceiverAddress();
        sAddReceiverAddress.setRequired(name);
        sAddReceiverAddress.setPhone(phone);
        sAddReceiverAddress.setAddressDetails(detailsAddress);
        sAddReceiverAddress.setProvinceCode(mProvince.getCode());
        sAddReceiverAddress.setCityCode(mCity.getCode());
        sAddReceiverAddress.setDistCode(mDist.getCode());
        sAddReceiverAddress.setDefault(isDefault);

        mController.sendAsyncMessage(ActionConstant.ADD_ADDRESS_ACTION, sAddReceiverAddress);
    }

    private boolean isObjectEmpty(RArea... rAreas) {
        for (RArea rArea : rAreas) {
            if (rArea == null) {
                return true;
            }
        }
        return false;
    }

    public void reGetAddress(View view) {
        //选择省市区
        mChooseAreaPopWindow = new ChooseAreaPopWindow(this);
        View parent_view = findViewById(R.id.parent_view);
        mChooseAreaPopWindow.setIOnChooseAddressCheangeListener(this);
        mChooseAreaPopWindow.show(parent_view);
    }

    @Override
    public void onChooseAddressChanged(RArea province, RArea city, RArea dist) {
        mProvince = province;
        mCity = city;
        mDist = dist;

        mChoose_province_tv.setText(province.getName() + city.getName() + dist.getName());
    }
}

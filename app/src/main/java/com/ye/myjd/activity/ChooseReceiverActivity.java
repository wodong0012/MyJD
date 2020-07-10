package com.ye.myjd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.ye.myjd.R;
import com.ye.myjd.adapter.ChooseReceiverAdapter;
import com.ye.myjd.bean.RReceiver;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.ShopCarController;

import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/4/2 22:12
 * @desc :
 */
public class ChooseReceiverActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    public static final String  CHOOSERECEIVER = "CHOOSERECEIVER";
    private ListView mReceiver_lv;
    private ChooseReceiverAdapter mAdapter;

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.CHOOSE_RECEIVEADDRESS_RESULT:
                loadReceiverAddress((List<RReceiver>) msg.obj);
                break;
        }
    }

    private void loadReceiverAddress(List<RReceiver> datas) {
        mAdapter.setData(datas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_activity);
        initController();
        initView();
        mReceiver_lv = findViewById(R.id.receiver_lv);
        mAdapter = new ChooseReceiverAdapter(this);
        mReceiver_lv.setAdapter(mAdapter);
        mController.sendAsyncMessage(ActionConstant.CHOOSE_RECEIVEADDRESS,0);
        mReceiver_lv.setOnItemClickListener(this);

    }

    @Override
    protected void initController() {
        mController = new ShopCarController(this);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RReceiver rReceiver = (RReceiver) mAdapter.getItem(position);
        if (rReceiver != null) {
            Intent intent = new Intent();
            intent.putExtra(CHOOSERECEIVER, rReceiver);
            setResult(0,intent);
            finish();
        }
    }
}

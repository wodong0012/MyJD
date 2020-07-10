package com.ye.myjd.ui.pop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ye.myjd.R;
import com.ye.myjd.bean.RArea;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.ShopCarController;
import com.ye.myjd.listener.IModeChangeListener;
import com.ye.myjd.listener.IOnChooseAddressCheangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/3/25 21:55
 * @desc : 商品搜索弹出框
 */
public class ChooseAreaPopWindow extends IPopupWindowProtocal implements View.OnClickListener, IModeChangeListener {

    private List<RArea> mProvinceDatas;
    private List<RArea> mCitys;
    private List<RArea> mDists;
    private RArea mProvince;
    private RArea mCity;
    private RArea mDist;
    private IOnChooseAddressCheangeListener mIOnChooseAddressCheangeListener;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case ActionConstant.PROVINCE_ACTION_RESULT:
                    loadProvinceData((List<RArea>) msg.obj);
                    break;
                case ActionConstant.CITY_ACTION_RESULT:
                    loadCity((List<RArea>) msg.obj);
                    break;
                case ActionConstant.AREA_ACTION_RESULT:
                    loadArea((List<RArea>) msg.obj);
                    break;
            }
        }
    };



    private void loadArea(List<RArea> areaDatas) {
        mDists = areaDatas;
        ArrayAdapter<String> mDistAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, initShowDatas(areaDatas));
        mDist_lv.setAdapter(mDistAdapter);

    }


    private void loadCity(List<RArea> datas) {
        mCitys = datas;
        ArrayAdapter<String> mCity = new ArrayAdapter<>(mContext,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, initShowDatas(datas));
        mCity_lv.setAdapter(mCity);
    }


    private void loadProvinceData(List<RArea> datas) {
        mProvinceDatas = datas;
        ArrayAdapter<String> mProvinceAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, initShowDatas(datas));
        mProvince_lv.setAdapter(mProvinceAdapter);
    }

    private List<String> initShowDatas(List<RArea> datas) {
        List<String> mProvinceData = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            mProvinceData.add(datas.get(i).getName());
        }
        return mProvinceData;
    }


    private ListView mProvince_lv;
    private ListView mCity_lv;
    private ListView mDist_lv;
    private ShopCarController mController;

    public ChooseAreaPopWindow(Context context) {
        super(context);
    }

    public void setIOnChooseAddressCheangeListener(IOnChooseAddressCheangeListener iOnChooseAddressCheangeListener) {
        mIOnChooseAddressCheangeListener = iOnChooseAddressCheangeListener;
    }

    @Override
    protected void initUi() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.address_pop_view, null, false);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        contentView.findViewById(R.id.left_v).setOnClickListener(this);
        contentView.findViewById(R.id.submit_tv).setOnClickListener(this);

        //省级
        mProvince_lv = contentView.findViewById(R.id.province_lv);
        mController.sendAsyncMessage(ActionConstant.PROVINCE_ACTION);
        mProvince_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mProvince = mProvinceDatas.get(position);
                loadCity(new ArrayList<RArea>());
                loadArea(new ArrayList<RArea>());
                mCity = null;
                mDist = null;
                String code = mProvince.getCode();
                mController.sendAsyncMessage(ActionConstant.CITY_ACTION, code);
            }
        });

        //
        mCity_lv = contentView.findViewById(R.id.city_lv);
        mCity_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCity = mCitys.get(position);
                mDist = null;
                String cityCode = mCity.getCode();
                mController.sendAsyncMessage(ActionConstant.AREA_ACTION, cityCode);
            }
        });
        mDist_lv = contentView.findViewById(R.id.dist_lv);
        mDist_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 mDist = mDists.get(position);
            }
        });


        //设置内容可点击
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.update();


    }

    @Override
    public void show(View anchor) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor, 0, 0);
        }
    }

    @Override
    protected void initController() {
        mController = new ShopCarController(mContext);
        mController.setIModeChangeListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_tv:
                if (mProvince != null && mCity_lv != null && mDist != null) {
                    mIOnChooseAddressCheangeListener.onChooseAddressChanged(mProvince, mCity, mDist);
                    dismiss();
                } else {
                    Toast.makeText(mContext, "请选择省市区!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.left_v:
                dismiss();
                break;
        }
    }

    @Override
    public void onModeChange(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
}

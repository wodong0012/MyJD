package com.ye.myjd.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ye.myjd.R;
import com.ye.myjd.bean.RArea;
import com.ye.myjd.bean.RReceiver;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.bean.RShopcar;
import com.ye.myjd.bean.SAddReceiverAddress;
import com.ye.myjd.bean.SOrderParams;
import com.ye.myjd.bean.SProductParams;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.util.NetWorkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author : WoDong
 * @date : 2020/3/30 20:54
 * @desc :
 */
public class ShopCarController extends UserController {
    public ShopCarController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object[] values) {
        switch (action) {
            case ActionConstant.SHOPCAR_ACTION:
                List<RShopcar> rShopcars = loadShopcar((mUserId));
                mIModeChangeListener.onModeChange(ActionConstant.SHOPCAR_ACTION_RESULT, rShopcars);
                break;
            case ActionConstant.DELETE_SHOPCARITEM_ACTION:
                RResult rResult = deleteChopCarItem((long) values[0]);
                mIModeChangeListener.onModeChange(ActionConstant.DELETE_SHOPCARITEM_ACTION_RESULT, rResult);
                break;
            case ActionConstant.RECEIVEADDRESS_ACTION:
                mIModeChangeListener.onModeChange(ActionConstant.RECEIVEADDRESS_ACTION_RESULT,
                        defalutReceiverAddress((Boolean) values[0]));
                break;
            case ActionConstant.PROVINCE_ACTION:
                List<RArea> rProvince = loadArea(NetWorkConstant.PROVINCE_URL, null);
                mIModeChangeListener.onModeChange(ActionConstant.PROVINCE_ACTION_RESULT, rProvince);
                break;
            case ActionConstant.CITY_ACTION:
                List<RArea> rCity = loadArea(NetWorkConstant.CITY_URL, (String) values[0]);
                mIModeChangeListener.onModeChange(ActionConstant.CITY_ACTION_RESULT, rCity);
                break;
            case ActionConstant.AREA_ACTION:
                List<RArea> rAreas = loadArea(NetWorkConstant.AREA_URL, (String) values[0]);
                mIModeChangeListener.onModeChange(ActionConstant.AREA_ACTION_RESULT, rAreas);
                break;
            case ActionConstant.ADD_ADDRESS_ACTION:
                mIModeChangeListener.onModeChange(ActionConstant.ADD_ADDRESS_ACTION_RESULT
                        , saveAddres((SAddReceiverAddress) values[0]));
                break;
            case ActionConstant.CHOOSE_RECEIVEADDRESS:
                List<RReceiver> rReceivers = loadReceiverAddress();
                mIModeChangeListener.onModeChange(ActionConstant.CHOOSE_RECEIVEADDRESS_RESULT, rReceivers);
                break;
            case ActionConstant.ADD_ORDER_ACTION:
                mIModeChangeListener.onModeChange(ActionConstant.ADD_ORDER_ACTION_RESUT,
                        addOrder((SOrderParams) values[0]));
                break;
        }
    }

    private RResult addOrder(SOrderParams sOrderParams) {
        sOrderParams.setUserId(mUserId);
        HashMap<String, String> params = new HashMap<>();
        //将对象转换成json
        String jsonProduct = JSON.toJSONString(sOrderParams);
        System.out.println(params);
        params.put("detail", jsonProduct);
        String json = NetWorkUtil.doPost(NetWorkConstant.ADD_ORDER_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        return rResult;
    }

    private List<RReceiver> loadReceiverAddress() {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", mUserId + "");
        params.put("isDefault", "false");
        String json = NetWorkUtil.doPost(NetWorkConstant.RECEIVEADDRESS_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            List<RReceiver> rReceivers = JSON.parseArray(rResult.getResult(), RReceiver.class);
            return rReceivers;
        }
        return null;
    }

    private RResult saveAddres(SAddReceiverAddress sAddReceiverAddress) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", mUserId + "");

        params.put("name", sAddReceiverAddress.getRequired());
        params.put("phone", sAddReceiverAddress.getPhone());
        params.put("provinceCode", sAddReceiverAddress.getProvinceCode());
        params.put("cityCode", sAddReceiverAddress.getCityCode());
        params.put("distCode", sAddReceiverAddress.getDistCode());
        params.put("addressDetails", sAddReceiverAddress.getAddressDetails());
        params.put("isDefault", sAddReceiverAddress.isDefault() + "");

        String json = NetWorkUtil.doPost(NetWorkConstant.ADDADDRESS_URL, params);

        System.out.println(json);

        RResult rResult = JSON.parseObject(json, RResult.class);
        return rResult;
    }

    private List<RArea> loadArea(String url, String code) {
        if (code != null) {
            url += "?fcode=" + code;
        }
        String json = NetWorkUtil.doGet(url);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            List<RArea> rAreas = JSON.parseArray(rResult.getResult(), RArea.class);
            return rAreas;
        }
        return new ArrayList<>();
    }


    private RReceiver defalutReceiverAddress(Boolean isDefault) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", mUserId + "");
        params.put("isDefault", isDefault + "");
        String json = NetWorkUtil.doPost(NetWorkConstant.RECEIVEADDRESS_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            List<RReceiver> rReceivers = JSON.parseArray(rResult.getResult(), RReceiver.class);
            if (rReceivers.size() > 0) {
                RReceiver rReceiver = rReceivers.get(0);
                return rReceiver;
            }
        }
        return null;
    }

    private RResult deleteChopCarItem(long pId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", mUserId + "");
        params.put("id", pId + "");
        String json = NetWorkUtil.doPost(NetWorkConstant.DELSHOPCAR, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        return rResult;
    }

    private List<RShopcar> loadShopcar(long userId) {

        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        String json = NetWorkUtil.doPost(NetWorkConstant.SHOPCAR_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            List<RShopcar> rShopcars = JSON.parseArray(rResult.getResult(), RShopcar.class);
            return rShopcars;
        }
        return new ArrayList<>();
    }
}

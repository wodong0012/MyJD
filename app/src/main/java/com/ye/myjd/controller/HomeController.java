package com.ye.myjd.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ye.myjd.bean.RBannerResult;
import com.ye.myjd.bean.RRecommendResult;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.bean.RRowsResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.util.NetWorkUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/3/18 20:49
 * @desc :
 */
public class HomeController extends BaseController {

    public HomeController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object[] values) {
        switch (action) {
            case ActionConstant.GET_BANNER_ACTION:
                List<RBannerResult> lists = getBanner((Integer) values[0]);
                mIModeChangeListener.onModeChange(ActionConstant.GET_BANNER_ACTION_RESULT, lists);
                break;
            case ActionConstant.SECKILL_ACTION:
                List<RRowsResult> seckill = getSeckill();
                mIModeChangeListener.onModeChange(ActionConstant.SECKILL_ACTION_RESULT, seckill);
                break;case ActionConstant.RECOMMEND_ACTION:
                    List<RRecommendResult> recommendLists = getRecommend();
                mIModeChangeListener.onModeChange(ActionConstant.RECOMMEND_ACTION_RESULT, recommendLists);
                break;
        }
    }

    private List<RRecommendResult> getRecommend() {
        String recommendJson = NetWorkUtil.doGet(NetWorkConstant.RECOMMEND_URL);
        RResult rResult = JSON.parseObject(recommendJson, RResult.class);
        if (rResult.isSuccess()) {
            try {
                org.json.JSONObject jsonObject = new org.json.JSONObject(rResult.getResult());
                String rowsJson = jsonObject.getString("rows");
                List<RRecommendResult> rRecommendResults = JSON.parseArray(rowsJson, RRecommendResult.class);
                return rRecommendResults;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<RRecommendResult>();
    }

    private List<RRowsResult> getSeckill() {
        String seckillJson = NetWorkUtil.doGet(NetWorkConstant.SECKILL_URL);
        RResult rResult = JSON.parseObject(seckillJson, RResult.class);
        if (rResult.isSuccess()) {
            try {
                org.json.JSONObject jsonObject = new org.json.JSONObject(rResult.getResult());
                String rowsJson = jsonObject.getString("rows");
                return JSON.parseArray(rowsJson, RRowsResult.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<RRowsResult>();
    }

    private List<RBannerResult> getBanner(int type) {
        List<RBannerResult> lists = new ArrayList<>();
        String urlPath = NetWorkConstant.BANNER_URL+"?adKind="+type;
        String json = NetWorkUtil.doGet(urlPath);
        RResult rResult = JSONObject.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            List<RBannerResult> rBannerResults = JSONObject.parseArray(rResult.getResult(), RBannerResult.class);
            return rBannerResults;
        }
        return lists;
    }
}

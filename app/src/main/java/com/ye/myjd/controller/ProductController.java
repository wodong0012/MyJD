package com.ye.myjd.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ye.myjd.bean.RComment;
import com.ye.myjd.bean.RCommentCount;
import com.ye.myjd.bean.RGoodComment;
import com.ye.myjd.bean.RProductInfo;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.fragment.ProductCommentFragment;
import com.ye.myjd.util.NetWorkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/3/28 17:26
 * @desc :
 */
public class ProductController extends UserController {
    public ProductController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object[] values) {
        switch (action) {
            case ActionConstant.PRODUCTINFO_ACTION:
                RProductInfo rProductInfo = loadProductInfo((long) values[0]);
                mIModeChangeListener.onModeChange(ActionConstant.PRODUCTINFO_ACTION_RESULT, rProductInfo);
                break;
            case ActionConstant.PRODUCTCOMMENT_ACTION:
                List<RGoodComment> rGoodComments = loadProductComment((long) values[0]);
                mIModeChangeListener.onModeChange(ActionConstant.PRODUCTCOMMENT_ACTION_RESULT, rGoodComments);
                break;
            case ActionConstant.COMMENTCOUNT_ACTION:
                RCommentCount rCommentCount = loadCommentCount((long) values[0]);
                mIModeChangeListener.onModeChange(ActionConstant.COMMENTCOUNT_ACTION_RESULT, rCommentCount);
                break;
            case ActionConstant.COMMENTDETAIL_ACTION:
                List<RComment> rComments = loadComment((long) values[0], (int) values[1]);
                mIModeChangeListener.onModeChange(ActionConstant.COMMENTDETAIL_ACTION_RESULT, rComments);
                break;
            case ActionConstant.TOSHOPCAR_ACTION:
                RResult rResult = toShopCar((long) values[0], (int) values[1], (String) values[2]);
                mIModeChangeListener.onModeChange(ActionConstant.TOSHOPCAR_ACTION_RESULT, rResult);
                break;
        }
    }

    private RResult toShopCar(long pId, int buyCount, String productVersion) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", mUserId + "");
        params.put("productId", pId + "");
        params.put("buyCount", buyCount + "");
        params.put("pversion", productVersion);
        String json = NetWorkUtil.doPost(NetWorkConstant.TOSHOPCAR_URL, params);
        System.out.println(json);
        RResult rResult = JSON.parseObject(json, RResult.class);
        return rResult;
    }

    private List<RComment> loadComment(long pId, int type) {
        HashMap<String, String> params = new HashMap<>();
        params.put("productId", pId + "");
        if (type == 4) {
            params.put("type", ProductCommentFragment.ALL_COMMENT + " ");
            params.put("hasImgCom", "true");
        }
        params.put("type", type + " ");
        String json = NetWorkUtil.doPost(NetWorkConstant.COMMENTDETAIL_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            List<RComment> rComments = JSON.parseArray(rResult.getResult(), RComment.class);
            return rComments;
        }
        return new ArrayList<>();
    }

    private RCommentCount loadCommentCount(long pId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("productId", pId + "");
        String json = NetWorkUtil.doPost(NetWorkConstant.COMMENTCOUNT_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            RCommentCount rCommentCount = JSON.parseObject(rResult.getResult(), RCommentCount.class);
            return rCommentCount;
        }
        return null;
    }

    private List<RGoodComment> loadProductComment(long pId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("productId", pId + "");
        params.put("type", 1 + "");
        String json = NetWorkUtil.doPost(NetWorkConstant.PRODUCTCOMMENT_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            List<RGoodComment> rGoodComments = JSON.parseArray(rResult.getResult(), RGoodComment.class);
            return rGoodComments;
        }
        return new ArrayList<>();
    }

    private RProductInfo loadProductInfo(long pId) {
        String url = NetWorkConstant.PRODUCTINFO_URL + "?id=" + pId;
        String json = NetWorkUtil.doGet(url);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            RProductInfo rProductInfo = JSON.parseObject(rResult.getResult(), RProductInfo.class);
            return rProductInfo;
        }
        return null;
    }
}

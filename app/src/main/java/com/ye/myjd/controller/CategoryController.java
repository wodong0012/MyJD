package com.ye.myjd.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ye.myjd.bean.RBrand;
import com.ye.myjd.bean.RProductList;
import com.ye.myjd.bean.RResult;
import com.ye.myjd.bean.RSubCategory;
import com.ye.myjd.bean.RTopCategoryResult;
import com.ye.myjd.bean.SProductList;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.util.NetWorkUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/3/20 21:26
 * @desc :
 */
public class CategoryController extends BaseController {
    public CategoryController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object[] values) {
        switch (action) {
            case ActionConstant.CATEGORY_ACTION:
                List<RTopCategoryResult> rTopCategoryResults = loadTopCategory();
                mIModeChangeListener.onModeChange(ActionConstant.CATEGORY_ACTION_RESULT,rTopCategoryResults);
                break;
            case ActionConstant.SECONDCATEGORY_ACTION:
                List<RSubCategory> list = secondCategory((Long)values[0]);
                mIModeChangeListener.onModeChange(ActionConstant.SECONDCATEGORY_ACTION_RESULT, list);
                break;
            case ActionConstant.BRAND_ACTION:
                List<RBrand> rBrands = loadBrand((Long) values[0]);
                mIModeChangeListener.onModeChange(ActionConstant.BRAND_ACTION_RESULT, rBrands);
                break;
            case ActionConstant.PRODUCT_LIST_ACTION:
                List<RProductList> rProductLists = sendProductList((SProductList) values[0]);
                mIModeChangeListener.onModeChange(ActionConstant.PRODUCT_LIST_ACTION_RESULT, rProductLists);
                break;
        }
    }

    private List<RProductList> sendProductList(SProductList sProductList) {
        HashMap<String, String> params = initProductListParams(sProductList);
        String json = NetWorkUtil.doPost(NetWorkConstant.SEND_PRODUCT_URL, params);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.isSuccess()) {
            try {
                JSONObject jsonObject = new JSONObject(rResult.getResult());
                String rowsJson = jsonObject.getString("rows");
                List<RProductList> rProductLists = JSON.parseArray(rowsJson, RProductList.class);
                return rProductLists;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<RProductList>();
    }

    private HashMap<String, String> initProductListParams(SProductList sProductList) {
        HashMap<String, String> params = new HashMap<>();
        params.put("categoryId", sProductList.getCategoryId()+"");
        params.put("filterType", sProductList.getFilterType() + "");
        if (sProductList.getSortType() != 0) {
            params.put("sortType", sProductList.getSortType() + "");

        }
        params.put("deliverChoose", sProductList.getDeliverChoose() + "");
        if (sProductList.getMinPrice() != 0 && sProductList.getMaxPrice() != 0) {
            params.put("minPrice", sProductList.getMinPrice() + "");
            params.put("maxPrice", sProductList.getMaxPrice() + "");
        }
        if (sProductList.getBrandId() != 0) {
            params.put("brandId", sProductList.getBrandId() + "");
        }
        return params;
    }

    private List<RBrand> loadBrand(Long topCategoryId) {

        String jsonStr = NetWorkUtil.doGet(NetWorkConstant.BRAND_URL + "?categoryId=" + topCategoryId);
        RResult rResult = JSON.parseObject(jsonStr, RResult.class);
        if (rResult.isSuccess()) {
            List<RBrand> rBrands = JSON.parseArray(rResult.getResult(), RBrand.class);
            return rBrands;
        }
        return new ArrayList<RBrand>();
    }

    private List<RSubCategory> secondCategory(long type) {
        String url = NetWorkConstant.CATEGORY_URL+"?parentId="+type;
        String secondJson = NetWorkUtil.doGet(url);
        RResult rResult = JSON.parseObject(secondJson, RResult.class);
        if (rResult.isSuccess()) {
            List<RSubCategory> list = JSON.parseArray(rResult.getResult(), RSubCategory.class);
            return list;
        }
        return new ArrayList<RSubCategory>();
    }

    private List<RTopCategoryResult> loadTopCategory() {
        String categoryJson = NetWorkUtil.doGet(NetWorkConstant.CATEGORY_URL);
        RResult rResult = JSON.parseObject(categoryJson, RResult.class);
        if (rResult.isSuccess()) {
            List<RTopCategoryResult> rTopCategoryResults = JSON.parseArray(rResult.getResult(), RTopCategoryResult.class);
            return rTopCategoryResults;
        }
        return new ArrayList<RTopCategoryResult>();
    }
}

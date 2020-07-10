package com.ye.myjd.activity;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.R;
import com.ye.myjd.bean.RSubCategory;
import com.ye.myjd.bean.RTopCategoryResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.controller.CategoryController;
import com.ye.myjd.listener.IModeChangeListener;
import com.ye.myjd.listener.IViewContainer;
import com.ye.myjd.ui.FlexiScrollView;
import com.ye.myjd.util.NetWorkUtil;

import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/3/20 21:59
 * @desc :
 */
public class SubCategoryView extends FlexiScrollView implements IViewContainer, IModeChangeListener, View.OnClickListener {
    private final static int sLineperSize = 3;
    public static final String TOPRODUCTLISTITEM = "TOPRODUCTLISTITEM";
    public static final String TOPRODUTOPCTCATEGORYID = "TOPRODUCTTOPCATEGORYID";
    private LinearLayout mChild_container_ll;
    private RTopCategoryResult mRTopCategoryResult;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case ActionConstant.SECONDCATEGORY_ACTION_RESULT:
                    secondCategory((List<RSubCategory>) msg.obj);
                    break;
            }
        }
    };

    private void secondCategory(List<RSubCategory> datas) {
        for (int i = 0; i < datas.size(); i++) {
            initSecondCategoryNameTv(datas, i);
            RSubCategory rSubCategory = datas.get(i);
            //获取三级分类数据
            List<RTopCategoryResult> rThirdCategoryList = JSON.parseArray(rSubCategory.getThirdCategory(), RTopCategoryResult.class);

            //计算行数
            int totalSize = rThirdCategoryList.size();
            int lines = totalSize / sLineperSize;

            //计算有没有余数
            int remainder = totalSize & sLineperSize;
            lines += (remainder == 0 ? 0 : 1);

            for (int a = 0; a < lines; a++) {
                //创建行的容器
                LinearLayout lineLl = new LinearLayout(getContext());
                LinearLayout.LayoutParams lineParmas = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lineParmas.setMargins(0, 10, 0, 0);
                //                lineLl.setOrientation(LinearLayout.HORIZONTAL);
                lineLl.setLayoutParams(lineParmas);
                mChild_container_ll.addView(lineLl);

                //计算每一行应该展示的列
                //sLinePerSize每一行最大的数*行的索引=整个3级分类数组的索引
                if (sLineperSize * a <= totalSize - 1) {
                    initThirdCategoryItem(rThirdCategoryList, sLineperSize * a, lineLl);

                }

                if (sLineperSize * a + 1 <= totalSize - 1) {
                    initThirdCategoryItem(rThirdCategoryList, sLineperSize * a + 1, lineLl);
                }

                if (sLineperSize * a + 2 <= totalSize - 1) {
                    initThirdCategoryItem(rThirdCategoryList, sLineperSize * a + 2, lineLl);
                }
            }
        }
    }



    private void initThirdCategoryItem(List<RTopCategoryResult> rThirdCategoryList, int index, LinearLayout lineLl) {
        RTopCategoryResult thirdCategory = rThirdCategoryList.get(index);
        //每一列容器
        LinearLayout columnLl = new LinearLayout(getContext());
        columnLl.setOrientation(LinearLayout.VERTICAL);
        columnLl.setGravity(Gravity.CENTER_HORIZONTAL);
        //给当前列设置点击事件
        columnLl.setOnClickListener(this);
        columnLl.setTag(thirdCategory);

        LinearLayout.LayoutParams columnParams = new LinearLayout.LayoutParams((getWidth() - 16) / 3, LinearLayout.LayoutParams.WRAP_CONTENT);
        columnLl.setLayoutParams(columnParams);
        //创建图片布局
        SmartImageView smartImageView = new SmartImageView(getContext());
        LinearLayout.LayoutParams bannerIvParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (getWidth() - 16) / 3);
        smartImageView.setLayoutParams(bannerIvParams);
        smartImageView.setImageUrl(NetWorkConstant.BASE_URL + thirdCategory.getBannerUrl());
        //将图片添加到列中
        columnLl.addView(smartImageView);

        //创建文本
        TextView thirdCaegoryNameTv = new TextView(getContext());
        LinearLayout.LayoutParams thirdCaegoryNameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        thirdCaegoryNameTv.setLayoutParams(thirdCaegoryNameParams);
        thirdCaegoryNameTv.setText(thirdCategory.getName());
        thirdCaegoryNameTv.setTextSize(15);
        //将文本添加进列容器中
        columnLl.addView(thirdCaegoryNameTv);
        lineLl.addView(columnLl);
    }

    private void initSecondCategoryNameTv(List<RSubCategory> datas, int i) {
        TextView name = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 16, 0, 4);
        RSubCategory rSubCategory = datas.get(i);
        name.setText(rSubCategory.getName());
        mChild_container_ll.addView(name);
    }

    private CategoryController mCategoryController;

    public SubCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onShow(Object... values) {
        mRTopCategoryResult = (RTopCategoryResult) values[0];

        //先清空容器
        mChild_container_ll.removeAllViews();
        //初始化图片
        initBannerIv();

        //请求2级分类数据
        mCategoryController.sendAsyncMessage(ActionConstant.SECONDCATEGORY_ACTION, mRTopCategoryResult.getId());


    }

    /**
     * 初始化顶部广告图片
     */
    private void initBannerIv() {
        //创建smart图片控件
        SmartImageView topSmart = new SmartImageView(getContext());
        //给图片设置大小
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
        params.setMargins(8, 8, 8, 8);
        topSmart.setLayoutParams(params);
        topSmart.setScaleType(ImageView.ScaleType.FIT_XY);
        String url = NetWorkConstant.BASE_URL + mRTopCategoryResult.getBannerUrl();
        topSmart.setImageUrl(url);
        //添加到父控件中
        mChild_container_ll.addView(topSmart);
    }

    /**
     * 当布局渲染完毕后会调用此方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initController();
        initView();


    }

    /**
     * 初始化控制器
     */
    private void initController() {
        mCategoryController = new CategoryController(getContext());
        mCategoryController.setIModeChangeListener(this);
    }

    private void initView() {

        mChild_container_ll = findViewById(R.id.child_container_ll);


    }

    /**
     * 数据加载完成的回调
     *
     * @param action 回调的action
     * @param values 回调带回来的数据
     */
    @Override
    public void onModeChange(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onClick(View v) {
        RTopCategoryResult thirdCategory = (RTopCategoryResult) v.getTag();
        Intent intent = new Intent(getContext(),ProductListActivity.class);
        intent.putExtra(TOPRODUTOPCTCATEGORYID, mRTopCategoryResult.getId());
        intent.putExtra(TOPRODUCTLISTITEM, thirdCategory.getId());
        getContext().startActivity(intent);
    }
}

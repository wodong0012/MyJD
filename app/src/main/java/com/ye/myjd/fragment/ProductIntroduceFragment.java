package com.ye.myjd.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.ye.myjd.R;
import com.ye.myjd.activity.ProductDetailsActivity;
import com.ye.myjd.adapter.ADPagerAdapter;
import com.ye.myjd.adapter.GoodCommentAdapter;
import com.ye.myjd.adapter.ProductTypeListAdapter;
import com.ye.myjd.bean.RGoodComment;
import com.ye.myjd.bean.RProductInfo;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.ProductController;
import com.ye.myjd.listener.INumberInputListener;
import com.ye.myjd.ui.NumberInputView;
import com.ye.myjd.util.FixedViewUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lean on 16/10/28.
 */

public class ProductIntroduceFragment extends BaseFragment implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener, INumberInputListener {

    private ViewPager mAd_vp;
    private ADPagerAdapter mAdPagerAdapter;
    private ProductDetailsActivity mProductDetailsActivity;
    private TextView mAd_indic_tv;
    private Timer mTimer;
    private TextView mProduct_name;
    private TextView mSelf_sale_tv;
    private TextView mDesc_tv;
    private TextView mRecommend_buy_tv;
    private ListView mProduct_versions_lv;
    private ProductTypeListAdapter mProductTypeListAdapter;
    private NumberInputView mNumber_input_et;
    private TextView mGood_rate_tip;
    private TextView mGood_rate_tv;
    private ListView mGood_comment_lv;
    private GoodCommentAdapter mGoodCommentAdapter;

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.PRODUCTINFO_ACTION_RESULT:
            loadProductInfo(msg.obj);
            break;
            case ActionConstant.PRODUCTCOMMENT_ACTION_RESULT:
                loadProductComment((List<RGoodComment>) msg.obj);
                break;
        }

    }

    private void loadProductComment(List<RGoodComment> datas) {
        mGoodCommentAdapter.setData(datas);
        mGoodCommentAdapter.notifyDataSetChanged();
        FixedViewUtil.setListViewHeightBasedOnChildren(mGood_comment_lv);
    }

    private void loadProductInfo(Object obj) {
        if (obj == null) {
            tip("数据异常");
            mProductDetailsActivity.finish();
            return;
        }
        final RProductInfo rProductInfo = (RProductInfo) obj;
        //设置pager滚动图片
        loadBrand(rProductInfo.getImgUrls());
        //设置商品名称
        mProduct_name.setText(rProductInfo.getName());
        //设置是否自营
        if (rProductInfo.isIfSaleOneself()) {
            mSelf_sale_tv.setText("自营");
        } else {
            mSelf_sale_tv.setText("商家");
        }

        //设置推荐介绍
        mDesc_tv.setText(rProductInfo.getRecomProduct());

        //设置推荐商品id
//        mRecommend_buy_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mActivity.finish();
//                long pId = rProductInfo.getRecomProductId();
//                System.out.println("pId:"+pId);
//                Intent intent = new Intent();
//                intent.setClass(mActivity, ProductDetailsActivity.class);
//                intent.putExtra(ProductListActivity.TODETAILS_KEY, 2);
//                startActivity(intent);
//            }
//        });
        List<String> types = JSON.parseArray(rProductInfo.getTypeList(), String.class);
        mProductTypeListAdapter.setData(types);
        mProductTypeListAdapter.notifyDataSetChanged();
        mProduct_versions_lv.performItemClick(null, 0, 0);

        FixedViewUtil.setListViewHeightBasedOnChildren(mProduct_versions_lv,10);

        //设置数量
        mNumber_input_et.setMax(rProductInfo.getStockCount());

        //设置好评率
        mGood_rate_tip.setText(rProductInfo.getFavcomRate()+"%好评");
        mGood_rate_tv.setText(rProductInfo.getCommentCount()+"人评价");
    }

    private void loadBrand(String imgUrls) {
        List<String> urls = JSON.parseArray(imgUrls, String.class);
        mAdPagerAdapter.setData(urls);
        mAdPagerAdapter.notifyDataSetChanged();

        mAd_indic_tv.setText("1/"+urls.size());
        //创建定时器滚动pager
        initAdBannerTimer(urls);
    }

    private void translateAdBannerItem(List<String> urls) {
        if (urls.size() != 0 && urls != null) {
            int currentItem = mAd_vp.getCurrentItem();
            currentItem++;
            if (currentItem > urls.size()-1) {
                currentItem = 0;
            }
            mAd_indic_tv.setText(currentItem+1+"/"+mAdPagerAdapter.getCount());
            mAd_vp.setCurrentItem(currentItem);
        }
    }

    private void initAdBannerTimer(final List<String> urls) {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        translateAdBannerItem(urls);
                    }
                });
            }
        }, 3000, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        System.out.println("sout");
//        if (mTimer != null) {
//            mTimer.cancel();
//            mTimer = null;
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (mTimer != null) {
//            mTimer.cancel();
//            mTimer = null;
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_introduce,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initView();
        initData();

        mController.sendAsyncMessage(ActionConstant.PRODUCTINFO_ACTION, mProductDetailsActivity.mProductId);
        mController.sendAsyncMessage(ActionConstant.PRODUCTCOMMENT_ACTION, mProductDetailsActivity.mProductId);

    }

    @Override
    protected void initController() {
        mController = new ProductController(mActivity);
        mController.setIModeChangeListener(this);

    }

    @Override
    protected void initView() {

        mAd_vp = mActivity.findViewById(R.id.ad_vp);
        mAdPagerAdapter = new ADPagerAdapter(mActivity);
        mAd_vp.setAdapter(mAdPagerAdapter);

        mAd_indic_tv = mActivity.findViewById(R.id.ad_indic_tv);

        //商品名称
        mProduct_name = mActivity.findViewById(R.id.name_tv);

        //是否自营
        mSelf_sale_tv = mActivity.findViewById(R.id.self_sale_tv);

        //商品推荐
        mDesc_tv = mActivity.findViewById(R.id.desc_tv);

        //跳转商品推荐页面
        mRecommend_buy_tv = mActivity.findViewById(R.id.recommend_buy_tv);

        //版本
        mProduct_versions_lv = mActivity.findViewById(R.id.product_versions_lv);
        mProductTypeListAdapter = new ProductTypeListAdapter(mActivity);
        mProduct_versions_lv.setAdapter(mProductTypeListAdapter);
        mProduct_versions_lv.setOnItemClickListener(this);

        //设置数量
        mNumber_input_et = mActivity.findViewById(R.id.number_input_et);
        mNumber_input_et.setListener(this);

        //好评率
        mGood_rate_tip = mActivity.findViewById(R.id.good_rate_tip);
        //评论人
        mGood_rate_tv = mActivity.findViewById(R.id.good_rate_tv);

        //评论详情
        mGood_comment_lv = mActivity.findViewById(R.id.good_comment_lv);
        mGoodCommentAdapter = new GoodCommentAdapter(mActivity);
        mGood_comment_lv.setAdapter(mGoodCommentAdapter);

    }

    @Override
    protected void initData() {
        mProductDetailsActivity = (ProductDetailsActivity) getActivity();
        mAd_vp.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int count = mAdPagerAdapter.getCount();
        mAd_indic_tv.setText(position+1+"/"+ count);
        if (position + 1 == count) {
            tip("已经是最后一张了");
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mProductTypeListAdapter.mCurrentTabPosition = position;
        mProductTypeListAdapter.notifyDataSetChanged();
        String version = (String) mProductTypeListAdapter.getItem(position);
        mProductDetailsActivity.mPversion = version;
    }

    @Override
    public void onTextChange(int number) {
        //设置用户购买的数量
        mProductDetailsActivity.mBuyCount = number;

    }
}

package com.ye.myjd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ye.myjd.R;
import com.ye.myjd.adapter.BrandListAdapter;
import com.ye.myjd.adapter.ProductListAdapter;
import com.ye.myjd.bean.RBrand;
import com.ye.myjd.bean.RProductList;
import com.ye.myjd.bean.SProductList;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.CategoryController;
import com.ye.myjd.listener.IProductStorChangeListener;
import com.ye.myjd.ui.FlexiListView;
import com.ye.myjd.ui.FlexiScrollView;
import com.ye.myjd.ui.pop.ProductSortPopuWindow;
import com.ye.myjd.util.FixedViewUtil;

import java.util.List;


/**
 * @author : WoDong
 * @date : 2020/3/24 21:55
 * @desc :
 */
public class ProductListActivity extends BaseActivity implements View.OnClickListener, IProductStorChangeListener, AdapterView.OnItemClickListener {

    private static final String TAG = "ProductListActivity";
    public static final String TODETAILS_KEY = "details_key";
    private long mCategoryId;
    private DrawerLayout mDrawerlayout;
    private long mTopCategoryId;
    private GridView mBrand_gv;
    private CategoryController mCategoryController;
    private BrandListAdapter mBrandlistAdapter;
    private TextView mAll_indicator;
    private TextView mSale_indicator;
    private TextView mPrice_indicator;
    private TextView mChoose_indicator;
    private ProductSortPopuWindow mProductSortPopuWindow;
    private SProductList mSProductList;
    private FlexiScrollView mSlide_view;
    private TextView mJd_take_tv;
    private TextView mPaywhenreceive_tv;
    private TextView mJusthasstock_tv;
    private EditText mMinPrice_et;
    private EditText mMaxPrice_et;
    private FlexiListView mProduct_lv;
    private ProductListAdapter mProductListAdapter;

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.BRAND_ACTION_RESULT:
                loadBrand((List<RBrand>) msg.obj);
                break;
            case ActionConstant.PRODUCT_LIST_ACTION_RESULT:
                loadProductList((List<RProductList>) msg.obj);
                break;
        }

    }

    private void loadProductList(List<RProductList> datas) {
        mProductListAdapter.setData(datas);
        mProductListAdapter.notifyDataSetChanged();
    }

    private void loadBrand(List<RBrand> datas) {
        mBrandlistAdapter.setData(datas);
        mBrandlistAdapter.notifyDataSetChanged();

        FixedViewUtil.setGridViewHeightBasedOnChildren(mBrand_gv, 3);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        initController();

        productView();
        productEvent();
        initSlideView();
        mCategoryController.sendAsyncMessage(ActionConstant.BRAND_ACTION, mTopCategoryId);
    }

    private void initSlideView() {
        mJd_take_tv = findViewById(R.id.jd_take_tv);
        mJd_take_tv.setOnClickListener(this);

        mPaywhenreceive_tv = findViewById(R.id.paywhenreceive_tv);
        mPaywhenreceive_tv.setOnClickListener(this);

        mJusthasstock_tv = findViewById(R.id.justhasstock_tv);
        mJusthasstock_tv.setOnClickListener(this);

        mMinPrice_et = findViewById(R.id.minPrice_et);
        mMaxPrice_et = findViewById(R.id.maxPrice_et);
    }


    private void productView() {
        mDrawerlayout = findViewById(R.id.drawerlayout);
        mBrand_gv = findViewById(R.id.brand_gv);
        //搜索栏下四个按钮
        mChoose_indicator = findViewById(R.id.choose_indicator);
        mAll_indicator = findViewById(R.id.all_indicator);
        mSale_indicator = findViewById(R.id.sale_indicator);
        mPrice_indicator = findViewById(R.id.price_indicator);
        mSlide_view = findViewById(R.id.slide_view);


        mBrandlistAdapter = new BrandListAdapter(this);
        mBrand_gv.setAdapter(mBrandlistAdapter);

        mProduct_lv = findViewById(R.id.product_lv);
        mProduct_lv.setOnItemClickListener(this);
        mProductListAdapter = new ProductListAdapter(this);
        mProduct_lv.setAdapter(mProductListAdapter);

    }

    private void productEvent() {
        mBrand_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBrandlistAdapter.mCurrentTabPosition = position;
                mBrandlistAdapter.notifyDataSetChanged();
                //点击记录下品牌id

            }
        });


        mAll_indicator.setOnClickListener(this);
        mSale_indicator.setOnClickListener(this);
        mPrice_indicator.setOnClickListener(this);
        mChoose_indicator.setOnClickListener(this);

    }

    @Override
    protected void initController() {
        mCategoryController = new CategoryController(this);
        mCategoryController.setIModeChangeListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mCategoryId = intent.getLongExtra(SubCategoryView.TOPRODUCTLISTITEM, 0);
        mTopCategoryId = intent.getLongExtra(SubCategoryView.TOPRODUTOPCTCATEGORYID, 0);
        if (mCategoryId == 0 || mTopCategoryId == 0) {
            tip("数据异常");
        }
        mSProductList = new SProductList();
        mSProductList.setCategoryId(mCategoryId);
        sendMessage();
    }

    @Override
    public void onClick(View v) {
        /*
        R.id.choose_indicator)
d.all_indicator);
id.sale_indicator);
.id.price_indicator);
         */
        switch (v.getId()) {
            case R.id.all_indicator:
                //综合
                if (mProductSortPopuWindow == null) {
                    mProductSortPopuWindow = new ProductSortPopuWindow(this);
                    mProductSortPopuWindow.setIPopupWindowProtocal(this);
                }
                mProductSortPopuWindow.show(v);
                break;
            case R.id.sale_indicator:
                //销量
                //                （1-销量 2-价格高到低 3-价格低到高）
                mSProductList.setSortType(1);
                sendMessage();
                break;
            case R.id.price_indicator:
                //价格
                //				排序  0-默认  1-销量 2-价格高到低  3-价格低到高
                int sortType = mSProductList.getSortType();
                if (sortType == 0 || sortType == 1 || sortType == 3) {
                    mSProductList.setSortType(2);
                }
                if (sortType == 0 || sortType == 1 || sortType == 2) {
                    mSProductList.setSortType(3);
                }
                sendMessage();
                break;
            case R.id.choose_indicator:
                //筛选
                mDrawerlayout.openDrawer(mSlide_view);
                break;
            case R.id.jd_take_tv:
            case R.id.paywhenreceive_tv:
            case R.id.justhasstock_tv:
                //选择类型（0-代表无选择 1代表京东配送 2-代表货到付款 4-代表仅看有货 3代表条件1+2 5代表条件1+4 6代表条件2+4）
                v.setSelected(!v.isSelected());
                break;
        }
    }

    //确定按钮
    public void chooseSearchClick(View view) {
        int deliverChose = 0;
        if (mJd_take_tv.isSelected()) {
            deliverChose += 1;
        }
        if (mPaywhenreceive_tv.isSelected()) {
            deliverChose += 2;
        }
        if (mJusthasstock_tv.isSelected()) {
            deliverChose += 4;
        }
        mSProductList.setDeliverChoose(deliverChose);

        //设置价格区间
        String minPrice = mMinPrice_et.getText().toString().trim();
        String maxPrice = mMaxPrice_et.getText().toString().trim();
        if (!TextUtils.isEmpty(minPrice)) {
            mSProductList.setMinPrice(Integer.valueOf(minPrice));
        }
        if (!TextUtils.isEmpty(maxPrice)) {
            mSProductList.setMaxPrice(Integer.valueOf(maxPrice));
        }

        //获取品牌id
        if (mBrandlistAdapter.mCurrentTabPosition != -1) {
            long brandId = mBrandlistAdapter.getItemId(mBrandlistAdapter.mCurrentTabPosition);
            mSProductList.setBrandId(brandId);
        }
        sendMessage();
        mDrawerlayout.closeDrawer(mSlide_view);
    }

    //重置按钮
    public void resetClick(View view) {
        mSProductList = new SProductList();
        mSProductList.setCategoryId(0);
        mJd_take_tv.setSelected(false);
        mPaywhenreceive_tv.setSelected(false);
        mJusthasstock_tv.setSelected(false);
                mMinPrice_et.setText("");
                mMaxPrice_et.setText("");
        mBrandlistAdapter.mCurrentTabPosition = -1;
        mBrandlistAdapter.notifyDataSetChanged();
        sendMessage();
        mDrawerlayout.closeDrawer(mSlide_view);
//



    }


    @Override
    public void onStorChanged(int action) {
        switch (action) {
            case IProductStorChangeListener.ALL_SORT:
                mAll_indicator.setText("综合");
                mSProductList.setFilterType(1);
                sendMessage();
                break;
            case IProductStorChangeListener.COMMENT_SORT:
                mAll_indicator.setText("评价");
                mSProductList.setFilterType(2);
                sendMessage();
                break;
            case IProductStorChangeListener.NEW_SORT:
                mAll_indicator.setText("新品");
                mSProductList.setFilterType(3);
                sendMessage();
                break;
        }

    }

    private void sendMessage() {
        mCategoryController.sendAsyncMessage(ActionConstant.PRODUCT_LIST_ACTION, mSProductList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long pId = mProductListAdapter.getItemId(position);
        Intent intent = new Intent();
        intent.setClass(this, ProductDetailsActivity.class);
        intent.putExtra(TODETAILS_KEY, pId);
        startActivity(intent);
    }
}

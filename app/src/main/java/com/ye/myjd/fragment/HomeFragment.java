package com.ye.myjd.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.MyJDApplication;
import com.ye.myjd.R;
import com.ye.myjd.activity.ProductDetailsActivity;
import com.ye.myjd.activity.ProductListActivity;
import com.ye.myjd.adapter.RecommendGridViewAdapter;
import com.ye.myjd.adapter.RecycleSecKillAdapter;
import com.ye.myjd.bean.RBannerResult;
import com.ye.myjd.bean.RLoginResult;
import com.ye.myjd.bean.RRecommendResult;
import com.ye.myjd.bean.RRowsResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.controller.HomeController;
import com.ye.myjd.util.FixedViewUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener {

	private ViewPager mAd_vp;
	private HomeVPAdapter mAdapter;
	private MyJDApplication mApplication;
	private RLoginResult mRloginResult;
	private LinearLayout mIndicatorLl;
	private Timer mTimer;
	private RecyclerView mRecyclerView;
	private RecycleSecKillAdapter mRecycleSecKillAdapter;
	private GridView mRecommend_gv;
	private RecommendGridViewAdapter mRecommendGridViewAdapterAdapter;

	@Override
	protected void userMessage(Message msg) {
		switch (msg.what) {
			case ActionConstant.GET_BANNER_ACTION_RESULT:
				getBanner((List<RBannerResult>)msg.obj);
				break;
			case ActionConstant.SECKILL_ACTION_RESULT:
				seckillResult((List<RRowsResult>)msg.obj);
				break;
			case ActionConstant.RECOMMEND_ACTION_RESULT:
				recommendResult((List< RRecommendResult >)msg.obj);
				break;
		}

	}

	private void recommendResult(List<RRecommendResult> datas) {
		mRecommendGridViewAdapterAdapter.setData(datas);
		mRecommendGridViewAdapterAdapter.notifyDataSetChanged();
		FixedViewUtil.setGridViewHeightBasedOnChildren(mRecommend_gv, 2);
	}

	private void seckillResult(List<RRowsResult> datas) {

		mRecycleSecKillAdapter.setData(datas);
		mRecycleSecKillAdapter.notifyDataSetChanged();
	}

	private void getBanner(List<RBannerResult> bannerResults) {
		if (bannerResults.size() == 0) return;
		mAdapter.setData(bannerResults);
		mAdapter.notifyDataSetChanged();
		//设置指示器
		initBannerIndicator(bannerResults);
		//启动一个定时器
		initBannerTimer(bannerResults);
	}

	private void initBannerTimer(final List<RBannerResult> bannerResults) {
		mTimer = new Timer();
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				changeBannerItem(bannerResults);
			}
		}, 3000, 3000);
	}

	private void changeBannerItem(final List<RBannerResult> bannerResults) {
		//获取主线程修改ui
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				int currentItem = mAd_vp.getCurrentItem();
				currentItem++;
				if (currentItem>bannerResults.size()-1) {
					currentItem=0;
				}
				mAd_vp.setCurrentItem(currentItem);
			}
		});
	}

	private void initBannerIndicator(List<RBannerResult> bannerResults) {
		for (int i = 0 ;i < bannerResults.size();i++) {
			View view = new View(mActivity);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
			params.setMargins(10, 0, 0, 0);
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.ad_indicator_bg);
			view.setEnabled(i == 0);
			mIndicatorLl.addView(view);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initController();
		initView();
		initData();
		initEvent();
		mController.sendAsyncMessage(ActionConstant.GET_BANNER_ACTION, 1);
		mController.sendAsyncMessage(ActionConstant.SECKILL_ACTION, 0);
		mController.sendAsyncMessage(ActionConstant.RECOMMEND_ACTION, 0);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mTimer != null) {
			mTimer.cancel();
		}
	}

	@Override
	protected void initView() {
		mAd_vp = mActivity.findViewById(R.id.ad_vp);
		mIndicatorLl =mActivity.findViewById(R.id.ad_indicator);
		mRecyclerView = mActivity.findViewById(R.id.horizon_recycle);
		mRecommend_gv = mActivity.findViewById(R.id.recommend_gv);


	}

	@Override
	protected void initData() {
		//设置广告栏数据
				mAdapter = new HomeVPAdapter();
		mApplication = (MyJDApplication) mActivity.getApplication();
		mRloginResult = mApplication.getRloginResult();
		mAd_vp.setAdapter(mAdapter);

		//设置秒杀模块数据
//		mSeckillAdapter = new SeckillAdapter(getActivity());
		//		mRecyclerView.setAdapter(mSeckillAdapter);
		//秒杀模块
		LinearLayoutManager layout = new LinearLayoutManager(getActivity());
		layout.setOrientation(RecyclerView.HORIZONTAL);
		mRecyclerView.setLayoutManager(layout);
		mRecycleSecKillAdapter = new RecycleSecKillAdapter(getActivity());
		mRecyclerView.setAdapter(mRecycleSecKillAdapter);

		//猜你喜欢模块
		mRecommendGridViewAdapterAdapter = new RecommendGridViewAdapter(getActivity());
		mRecommend_gv.setAdapter(mRecommendGridViewAdapterAdapter);
		mRecommend_gv.setOnItemClickListener(this);

	}

	@Override
	protected void initController() {
		mController = new HomeController(mActivity);
		mController.setIModeChangeListener(this);
	}

	@Override
	protected void initEvent() {
		mAd_vp.setOnPageChangeListener(this);

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		//		修改指示器的样式
		int childCount = mIndicatorLl.getChildCount();
		for (int i = 0; i < childCount; i++) {
			mIndicatorLl.getChildAt(i).setEnabled(i==position);
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		long pId = mRecommendGridViewAdapterAdapter.getItemId(position);
		Intent intent = new Intent();
		intent.setClass(getActivity(), ProductDetailsActivity.class);
		intent.putExtra(ProductListActivity.TODETAILS_KEY, pId);
		startActivity(intent);
	}


	private class HomeVPAdapter extends PagerAdapter{
		private List<RBannerResult> mbannerResults;
		private ArrayList<SmartImageView> mChildViews;

		public void setData(List<RBannerResult> bannerResults) {
			mbannerResults = bannerResults;
			mChildViews=new ArrayList<SmartImageView>(mbannerResults.size());
				for (RBannerResult rBannerResult: bannerResults) {
				SmartImageView smiv = new SmartImageView(mActivity);
				//				LayoutParams 添加到哪个容器 就应该使用哪个容器的LayoutParams
				smiv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
				smiv.setScaleType(ImageView.ScaleType.FIT_XY);
				smiv.setImageUrl(NetWorkConstant.BASE_URL+rBannerResult.getAdUrl());
				mChildViews.add(smiv);
			}
		}

		@Override
		public int getCount() {
			return mbannerResults != null?mbannerResults.size():0;
	}

		@Override
		public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
			return view == object;
		}

		@NonNull
		@Override
		public Object instantiateItem(@NonNull ViewGroup container, int position) {
			SmartImageView smartImageView = mChildViews.get(position);
			container.addView(smartImageView);
			return smartImageView;
		}

		@Override
		public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
			SmartImageView smartImageView = mChildViews.get(position);
			container.removeView(smartImageView);
		}
	}
}

package com.ye.myjd.fragment;


import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.ye.myjd.R;
import com.ye.myjd.activity.SubCategoryView;
import com.ye.myjd.adapter.CategoryListViewAdapter;
import com.ye.myjd.bean.RTopCategoryResult;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.CategoryController;

import java.util.List;

public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener {

	private ListView mTop_lv;
	private CategoryListViewAdapter mCategoryListViewAdapter;
	private CategoryController mCategoryController;
	private SubCategoryView mSubCategoryView;


	@Override
	protected void userMessage(Message msg) {
		switch (msg.what) {
			case ActionConstant.CATEGORY_ACTION_RESULT:
				topCategory((List<RTopCategoryResult>) msg.obj);
				break;
		}
	}

	private void topCategory(List<RTopCategoryResult> datas) {
		System.out.println("topCategory:"+datas);
		mCategoryListViewAdapter.setData(datas);
		mCategoryListViewAdapter.notifyDataSetChanged();
		mTop_lv.performItemClick(null, 0, 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_category, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initController();
		//初始化布局
		initView();
		//初始化数据
		initData();
		//初始化事件
		initEvent();

		mCategoryController.sendAsyncMessage(ActionConstant.CATEGORY_ACTION, 0);
	}

	@Override
	protected void initController() {
		mCategoryController = new CategoryController(mActivity);
		mCategoryController.setIModeChangeListener(this);
	}

	/**
	 * 初始化布局
	  */
	@Override
	protected void initView() {
		mTop_lv = mActivity.findViewById(R.id.top_lv);
		mCategoryListViewAdapter = new CategoryListViewAdapter(mActivity);
		mTop_lv.setAdapter(mCategoryListViewAdapter);
		//找到二级分类
		mSubCategoryView =mActivity.findViewById(R.id.subcategory);
	}

	/**
	 * 初始化数据
	 */
	@Override
	protected void initData() {

	}

	/**
	* 初始化事件
	*/
	@Override
	protected void initEvent() {
		mTop_lv.setOnItemClickListener(this);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//记录那个item被点击了
		mCategoryListViewAdapter.currentItem = position;
		//刷新adapter
		mCategoryListViewAdapter.notifyDataSetChanged();
		RTopCategoryResult item = (RTopCategoryResult) mCategoryListViewAdapter.getItem(position);
		mSubCategoryView.onShow(item);

	}
}

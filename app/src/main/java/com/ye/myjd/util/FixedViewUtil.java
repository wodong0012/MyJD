package com.ye.myjd.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class FixedViewUtil {

    public static void setGridViewHeightBasedOnChildren(GridView gridView,int col) {
        // 获取gridView的adapter
        ListAdapter gridAdapter = gridView.getAdapter();
        if (gridAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < gridAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View gridItem = gridAdapter.getView(i, null, gridView);
            gridItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += gridItem.getMeasuredHeight();
            totalHeight += gridView.getVerticalSpacing();
            if (i==gridAdapter.getCount()-1) {
                totalHeight += gridView.getVerticalSpacing();
            }
        }
        // 获取listview的布局参数
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        gridView.setLayoutParams(params);
    }

    public static void setListViewHeightBasedOnChildren(ListView lv){
        ListAdapter listAdapter = lv.getAdapter();
        int listViewHeight = 0;
        int adaptCount = listAdapter.getCount();
        for(int i=0;i<adaptCount;i++){
            View temp = listAdapter.getView(i,null,lv);
            temp.measure(0,0);
            listViewHeight += temp.getMeasuredHeight();
        }
        LayoutParams layoutParams = lv.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = listViewHeight;
        lv.setLayoutParams(layoutParams);
    }
    public static void setListViewHeightBasedOnChildren(ListView lv , int verticalSpacing){
        ListAdapter listAdapter = lv.getAdapter();
        int listViewHeight = 0;
        int adaptCount = listAdapter.getCount();
        for(int i=0;i<adaptCount;i++){
            View temp = listAdapter.getView(i,null,lv);
            temp.measure(0,0);
            listViewHeight += temp.getMeasuredHeight();
            listViewHeight += verticalSpacing;
        }
        listViewHeight -= verticalSpacing;
        LayoutParams layoutParams = lv.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = listViewHeight;
        lv.setLayoutParams(layoutParams);
    }

}

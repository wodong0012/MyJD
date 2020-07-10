package com.ye.myjd.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ye.myjd.R;
import com.ye.myjd.bean.RTopCategoryResult;


/**
 * @author : WoDong
 * @date : 2020/3/20 21:29
 * @desc :
 */
public class CategoryListViewAdapter extends JDBaseAdapter<RTopCategoryResult> {

    public int currentItem = -1;
    public CategoryListViewAdapter(Activity c) {
        super(c);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryListViewHolder categoryListViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.top_category_item, parent, false);
            categoryListViewHolder = new CategoryListViewHolder(convertView);
            convertView.setTag(categoryListViewHolder);
        } else {
            categoryListViewHolder = (CategoryListViewHolder) convertView.getTag();
        }
        categoryListViewHolder.initData(position);

        return convertView;
    }

   class CategoryListViewHolder{
       private View divider;
       private TextView name_tv;

       public CategoryListViewHolder(View view) {
            divider = view.findViewById(R.id.divider);
            name_tv = view.findViewById(R.id.name_tv);
       }

       public void initData(int position) {
           RTopCategoryResult rTopCategoryResult = mDatas.get(position);
           name_tv.setText(rTopCategoryResult.getName());
           if (position == currentItem) {
                name_tv.setSelected(true);
                name_tv.setBackgroundResource(R.drawable.tongcheng_all_bg01);
                divider.setVisibility(View.INVISIBLE);
           } else {
                name_tv.setSelected(false);
               name_tv.setBackgroundColor(0xFFFAFAFA);
               divider.setVisibility(View.VISIBLE);
           }
       }
   }

    @Override
    public Object getItem(int position) {
        return mDatas != null ? mDatas.get(position):null;
    }
}

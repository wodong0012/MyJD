package com.ye.myjd.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.R;
import com.ye.myjd.bean.RProductList;
import com.ye.myjd.bean.RReceiver;
import com.ye.myjd.cons.NetWorkConstant;

/**
 * @author : WoDong
 * @date : 2020/3/27 18:44
 * @desc :
 */
public class ChooseReceiverAdapter extends JDBaseAdapter<RReceiver> {
    public ChooseReceiverAdapter(Activity c) {
        super(c);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductListHolder productListHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.choose_address_item_view, parent, false);
            productListHolder = new ProductListHolder(convertView);
            convertView.setTag(productListHolder);
        } else {
            productListHolder = (ProductListHolder) convertView.getTag();
        }
        productListHolder.setData(position);
        return convertView;
    }

    class ProductListHolder implements View.OnClickListener {
        private ImageView isDeafult_iv;
        private TextView name_tv;
        private TextView phone_tv;
        private TextView address_tv;
        private TextView delete_tv;

        public ProductListHolder(View convertView) {
            isDeafult_iv = convertView.findViewById(R.id.isDeafult_iv);
            name_tv = convertView.findViewById(R.id.name_tv);
            phone_tv = convertView.findViewById(R.id.phone_tv);
            address_tv = convertView.findViewById(R.id.address_tv);
            delete_tv = convertView.findViewById(R.id.delete_tv);
        }

        public void setData(int position) {
            RReceiver rReceiver = mDatas.get(position);
            name_tv.setText(rReceiver.getReceiverName());
            phone_tv.setText(rReceiver.getReceiverPhone());
            address_tv.setText(rReceiver.getReceiverAddress());
            isDeafult_iv.setVisibility(rReceiver.isDefault() ? View.VISIBLE : View.INVISIBLE);
            delete_tv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //思路：设置设置监听器通知activity 要删除此地址 将地址的id传给activity
            // activity请求网络删除地址，回调刷新adapter
        }
    }

    @Override
    public long getItemId(int position) {
        return mDatas != null ? mDatas.get(position).getId() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDatas != null ? mDatas.get(position) : null;
    }
}

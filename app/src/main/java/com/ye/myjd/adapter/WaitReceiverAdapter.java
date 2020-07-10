package com.ye.myjd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ye.myjd.R;
import com.ye.myjd.bean.ROrderByStatus;
import com.ye.myjd.listener.IOrderConfirmReceiverListener;

/**
 * @author : WoDong
 * @date : 2020/4/5 17:33
 * @desc :
 */
public class WaitReceiverAdapter extends OrderBaseAdapter {
    private IOrderConfirmReceiverListener mListener;

    public WaitReceiverAdapter(Context c) {
        super(c);
    }

    public void setIOrderConfirmReceiverListener(IOrderConfirmReceiverListener listener) {
        mListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WaitPayHolder waitPayHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.all_order_item, parent, false);
            waitPayHolder = new WaitPayHolder(convertView);
            convertView.setTag(waitPayHolder);
        } else {
            waitPayHolder = (WaitPayHolder) convertView.getTag();
        }
        waitPayHolder.setData(position);
        return convertView;
    }

    class WaitPayHolder {
        private LinearLayout p_container_ll;
        private TextView order_state_tv;
        private TextView order_no_tv;
        private TextView price_tv;
        private Button do_btn;

        public WaitPayHolder(View view) {
            p_container_ll = view.findViewById(R.id.p_container_ll);
            order_state_tv = view.findViewById(R.id.order_state_tv);
            order_no_tv = view.findViewById(R.id.order_no_tv);
            price_tv = view.findViewById(R.id.price_tv);
            do_btn = view.findViewById(R.id.do_btn);
            do_btn.setText("确认收货");
            do_btn.setVisibility(View.VISIBLE);
        }

        public void setData(int position) {
            final ROrderByStatus rOrderByStatus = mDatas.get(position);
            initOrderStatus(rOrderByStatus.getStatus(), order_state_tv);
            initProductItems(p_container_ll, rOrderByStatus.getItems());
            //            order_state_tv.setText(rOrderByStatus.getStatus());
            order_no_tv.setText("订单编号：" + rOrderByStatus.getOrderNum());
            price_tv.setText("￥ " + rOrderByStatus.getTotalPrice());
            do_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onOrderReceiver(rOrderByStatus.getOid());
                }
            });

        }
    }
}

package com.ye.myjd.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.R;
import com.ye.myjd.bean.RRowsResult;
import com.ye.myjd.cons.NetWorkConstant;

import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/3/19 21:51
 * @desc :
 */
public class RecycleSecKillAdapter extends RecyclerView.Adapter<RecycleSecKillAdapter.SeckillViewHolder> {

    private Context mContext;
    private List<RRowsResult> mDatas;

    public RecycleSecKillAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<RRowsResult> datas) {
        mDatas = datas;

    }

    @NonNull
    @Override
    public SeckillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = View.inflate(mContext, R.layout.home_seckill_item, null);
        return new SeckillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeckillViewHolder holder, int position) {
        holder.setData(position);
    }


    @Override
    public int getItemCount() {
        return mDatas !=null?mDatas.size():0;
    }

    public class SeckillViewHolder extends RecyclerView.ViewHolder {

        private final SmartImageView mImage_iv;
        private final TextView mNowprice_tv;
        private final TextView mNormalprice_tv;

        public SeckillViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage_iv = itemView.findViewById(R.id.image_iv);
            mNowprice_tv = itemView.findViewById(R.id.nowprice_tv);
            mNormalprice_tv = itemView.findViewById(R.id.normalprice_tv);
        }

        public void setData(int position) {
            RRowsResult rRowsResult =  mDatas.get(position);
            mImage_iv.setImageUrl(NetWorkConstant.BASE_URL+ rRowsResult.getIconUrl());
            mNowprice_tv.setText(rRowsResult.getPointPrice()+"");
            mNormalprice_tv.setText("￥ "+ rRowsResult.getAllPrice()+" ");
            mNormalprice_tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        }
    }
}

package com.ye.myjd.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ye.loopj.android.image.SmartImageView;
import com.ye.myjd.R;
import com.ye.myjd.bean.RShopcar;
import com.ye.myjd.cons.NetWorkConstant;
import com.ye.myjd.listener.IShopcarCheckChanngeListener;
import com.ye.myjd.listener.IShopcarDeleteListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : WoDong
 * @date : 2020/3/30 20:52
 * @desc :
 */
public class ShopcarLvAdapter extends JDBaseAdapter<RShopcar> {

    private static ArrayList<Boolean> sItemChecked = new ArrayList<>();
    private IShopcarCheckChanngeListener mIShopCarCheckChangeListener;
    private IShopcarDeleteListener mIShopcarDeleteListener;

    public ShopcarLvAdapter(Activity c) {
        super(c);
    }

    @Override
    public void setData(List<RShopcar> datas) {
        super.setData(datas);
        sItemChecked.clear();
        for (int i = 0; i < datas.size(); i++) {
            sItemChecked.add(false);
        }
    }

    public void setCheck(int position) {
        //判断item是否被选中
        sItemChecked.set(position, !sItemChecked.get(position));
        //adapter内部自己刷新界面
        notifyDataSetChanged();
        FragmentNotify();
    }

    public void setIShopcarDeleteListener(IShopcarDeleteListener listener) {
        mIShopcarDeleteListener = listener;
    }

    private void FragmentNotify() {
        //通知外部的fragment刷新界面
        if (mIShopCarCheckChangeListener != null) {
            int buyCount = 0;
            for (int i = 0; i < sItemChecked.size(); i++) {
                if (sItemChecked.get(i)) {
                    buyCount++;
                }
            }
            mIShopCarCheckChangeListener.onBuyCountChanged(buyCount);

            double newestPrice = 0;
            for (int i = 0; i < sItemChecked.size(); i++) {
                if (sItemChecked.get(i)) {
                    RShopcar rShopcar = mDatas.get(i);
                    newestPrice += rShopcar.getPprice();
                    System.out.println(newestPrice);
                }
            }
            mIShopCarCheckChangeListener.onTotalPriceChanged(newestPrice);
        }
    }

    public void setIShopcarCheckChanngeListener(IShopcarCheckChanngeListener listener) {
        mIShopCarCheckChangeListener = listener;
    }

    public void setAllCheck(boolean isChecked) {
        for (int i = 0; i < sItemChecked.size(); i++) {
            sItemChecked.set(i, isChecked);
        }
        FragmentNotify();
        notifyDataSetChanged();

    }

   public boolean ifItemChecked() {
       for (int i = 0; i < sItemChecked.size(); i++) {
           if (sItemChecked.get(i)) {
               return true;
           }
       }
       return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShopcarHolder shopcarHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.shopcar_lv_item, parent, false);
            shopcarHolder = new ShopcarHolder(convertView);
            convertView.setTag(shopcarHolder);
        } else {
            shopcarHolder = (ShopcarHolder) convertView.getTag();
        }

        shopcarHolder.setData(position);

        return convertView;
    }

    /**
     * 获取被选中的商品
     * @return 如果没有被选中的商品则返回空集合
     */
    public ArrayList<RShopcar> getCheckItems() {
        ArrayList<RShopcar> rShopcars = new ArrayList<>();

        if (sItemChecked.size() != 0) {
            for (int i = 0; i < sItemChecked.size(); i++) {
                if (sItemChecked.get(i) == true)
                rShopcars.add(mDatas.get(i));
            }
            return rShopcars;
        }
        return new ArrayList<>();
    }

    class ShopcarHolder {

        private CheckBox cbx;
        private SmartImageView product_iv;
        private TextView productName_tv;
        private TextView version_tv;
        private TextView price_tv;
        private TextView buyCount_tv;
        private Button delete_product;

        public ShopcarHolder(View view) {
            cbx = view.findViewById(R.id.cbx);
            product_iv = view.findViewById(R.id.product_iv);
            productName_tv = view.findViewById(R.id.name_tv);
            version_tv = view.findViewById(R.id.version_tv);
            price_tv = view.findViewById(R.id.price_tv);
            buyCount_tv = view.findViewById(R.id.buyCount_tv);
            delete_product = view.findViewById(R.id.delete_product);
        }

        public void setData(int position) {
            final RShopcar rShopcar = mDatas.get(position);
            product_iv.setImageUrl(NetWorkConstant.BASE_URL + rShopcar.getPimageUrl());
            productName_tv.setText(rShopcar.getPname());
            version_tv.setText(rShopcar.getPversion());
            price_tv.setText("￥ " + rShopcar.getPprice());
            buyCount_tv.setText("x " + rShopcar.getBuyCount());
            cbx.setChecked(sItemChecked.get(position));
            delete_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIShopcarDeleteListener != null) {
                        mIShopcarDeleteListener.deleteProduct(rShopcar.getId());
                    }
                }
            });
        }

    }


}
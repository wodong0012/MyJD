package com.ye.myjd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.ye.myjd.R;
import com.ye.myjd.activity.ProductDetailsActivity;
import com.ye.myjd.cons.NetWorkConstant;

/**
 * Created by lean on 16/10/28.
 */
public class ProductDetailsFragment extends BaseFragment {

    private WebView mDetails_webview;
    private ProductDetailsActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_details,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (ProductDetailsActivity) getActivity();
        mDetails_webview = mActivity.findViewById(R.id.details_webview);
        initData();
    }

    @Override
    protected void initData() {
        mDetails_webview.loadUrl(NetWorkConstant.PRODUCTDETAIL_URL +"?productId="+mActivity.mProductId);

        mDetails_webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mDetails_webview.getSettings().setJavaScriptEnabled(true);
    }
}

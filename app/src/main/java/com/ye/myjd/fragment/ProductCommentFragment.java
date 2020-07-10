package com.ye.myjd.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ye.myjd.R;
import com.ye.myjd.activity.ProductDetailsActivity;
import com.ye.myjd.adapter.CommentAdapter;
import com.ye.myjd.bean.RComment;
import com.ye.myjd.bean.RCommentCount;
import com.ye.myjd.cons.ActionConstant;
import com.ye.myjd.controller.ProductController;

import java.util.List;

/**
 * Created by lean on 16/10/28.
 */

public class ProductCommentFragment extends BaseFragment {

    private ProductDetailsActivity mActivity;
    private TextView mAll_comment_tv;
    private TextView mAll_comment_tip;
    private TextView mPositive_comment_tv;
    private TextView mPositive_comment_tip;
    private TextView mCenter_comment_tv;
    private TextView mCenter_comment_tip;
    private TextView mNagetive_comment_tv;
    private TextView mNagetive_comment_tip;
    private TextView mHas_image_comment_tv;
    private TextView mHas_image_comment_tip;

    public final static int ALL_COMMENT = 0;
    public final static int POSITIVE_COMMENT = 1;
    public final static int CENTER_COMMENT  = 2;
    public final static int NAGETIVE_COMMENT  = 3;
    public final static int HAS_IMAGE_COMMENT  = 4;
    private ListView mComment_lv;
    private CommentAdapter mCommentAdapter;

    @Override
    protected void userMessage(Message msg) {
        switch (msg.what) {
            case ActionConstant.COMMENTCOUNT_ACTION_RESULT:
                loadCommentCount(msg.obj);
                break;
            case ActionConstant.COMMENTDETAIL_ACTION_RESULT:
                loadComment((List< RComment>) msg.obj);
                break;
        }
    }

    private void loadComment(List<RComment> datas) {
        mCommentAdapter.setData(datas);
        mCommentAdapter.notifyDataSetChanged();
    }

    private void loadCommentCount(Object obj) {
        if (obj != null) {
            RCommentCount rCommentCount = (RCommentCount) obj;
            mAll_comment_tv.setText(rCommentCount.getAllComment() + "");
            mPositive_comment_tv.setText(rCommentCount.getPositiveCom() + "");
            mCenter_comment_tv.setText(rCommentCount.getModerateCom() + "");
            mNagetive_comment_tv.setText(rCommentCount.getNegativeCom() + "");
            mHas_image_comment_tv.setText(rCommentCount.getHasImgCom() + "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_comment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = ((ProductDetailsActivity) getActivity());
        initController();
        initView();

        mController.sendAsyncMessage(ActionConstant.COMMENTCOUNT_ACTION, mActivity.mProductId);

    }

    @Override
    protected void initController() {
        mController = new ProductController(mActivity);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initView() {
        //全部评论
        mAll_comment_tv = mActivity.findViewById(R.id.all_comment_tv);
        mAll_comment_tip = mActivity.findViewById(R.id.all_comment_tip);
        mActivity.findViewById(R.id.all_comment_ll).setOnClickListener(this);

        //好评
        mPositive_comment_tv = mActivity.findViewById(R.id.positive_comment_tv);
        mPositive_comment_tip = mActivity.findViewById(R.id.positive_comment_tip);
        mActivity.findViewById(R.id.positive_comment_ll).setOnClickListener(this);

        //中评
        mCenter_comment_tv = mActivity.findViewById(R.id.center_comment_tv);
        mCenter_comment_tip = mActivity.findViewById(R.id.center_comment_tip);
        mActivity.findViewById(R.id.center_comment_ll).setOnClickListener(this);

        //差评
        mNagetive_comment_tv = mActivity.findViewById(R.id.nagetive_comment_tv);
        mNagetive_comment_tip = mActivity.findViewById(R.id.nagetive_comment_tip);
        mActivity.findViewById(R.id.nagetive_comment_ll).setOnClickListener(this);

        //有图
        mHas_image_comment_tv = mActivity.findViewById(R.id.has_image_comment_tv);
        mHas_image_comment_tip = mActivity.findViewById(R.id.has_image_comment_tip);
        mActivity.findViewById(R.id.has_image_comment_ll).setOnClickListener(this);

        mActivity.findViewById(R.id.all_comment_ll).performClick();

        mComment_lv = mActivity.findViewById(R.id.comment_lv);
        mCommentAdapter = new CommentAdapter(mActivity);
        mComment_lv.setAdapter(mCommentAdapter);
    }

    @Override
    public void onClick(View v) {
        defaultIndcator();
        switch (v.getId()) {
            case R.id.all_comment_ll:
                mAll_comment_tv.setSelected(true);
                mAll_comment_tip.setSelected(true);
                sendCommentDetail(ALL_COMMENT);
                break;
            case R.id.positive_comment_ll:
                mPositive_comment_tip.setSelected(true);
                mPositive_comment_tv.setSelected(true);
                sendCommentDetail(POSITIVE_COMMENT);
                break;
            case R.id.center_comment_ll:
                mCenter_comment_tv.setSelected(true);
                mCenter_comment_tip.setSelected(true);
                sendCommentDetail(CENTER_COMMENT);
                break;
            case R.id.nagetive_comment_ll:
                mNagetive_comment_tv.setSelected(true);
                mNagetive_comment_tip.setSelected(true);
                sendCommentDetail(NAGETIVE_COMMENT);
                break;
            case R.id.has_image_comment_ll:
                mHas_image_comment_tv.setSelected(true);
                mHas_image_comment_tip.setSelected(true);
                sendCommentDetail(HAS_IMAGE_COMMENT);
                break;
        }
    }

    private void sendCommentDetail(int type) {
        mController.sendAsyncMessage(ActionConstant.COMMENTDETAIL_ACTION, mActivity.mProductId,type);
    }

    private void defaultIndcator() {
        mAll_comment_tv.setSelected(false);
        mAll_comment_tip.setSelected(false);

        mPositive_comment_tip.setSelected(false);
        mPositive_comment_tv.setSelected(false);

        mCenter_comment_tv.setSelected(false);
        mCenter_comment_tip.setSelected(false);

        mNagetive_comment_tv.setSelected(false);
        mNagetive_comment_tip.setSelected(false);

        mHas_image_comment_tv.setSelected(false);
        mHas_image_comment_tip.setSelected(false);
    }
}

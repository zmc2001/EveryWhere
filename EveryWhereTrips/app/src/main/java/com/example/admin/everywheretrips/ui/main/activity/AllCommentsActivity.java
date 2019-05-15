package com.example.admin.everywheretrips.ui.main.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.bean.AllCommentsBean;
import com.example.admin.everywheretrips.presenter.AllCommentsPresenter;
import com.example.admin.everywheretrips.ui.main.adapter.main.AllCommentsRlvAdapter;
import com.example.admin.everywheretrips.view.main.AllCommentsView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllCommentsActivity extends BaseActivity<AllCommentsView, AllCommentsPresenter>
        implements AllCommentsView {

    @BindView(R.id.allcomments_back)
    ImageView mAllcommentsBack;
    @BindView(R.id.allcomments_tb)
    Toolbar mAllcommentsTb;
    @BindView(R.id.allcomments_rlv)
    RecyclerView mAllcommentsRlv;
    private ArrayList<AllCommentsBean.ResultBean.ReviewsBean> mList;
    private AllCommentsRlvAdapter mAdapter;
    private int mAllcid;
    private int page = 1;

    @Override
    protected AllCommentsPresenter initPresenter() {
        return new AllCommentsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_all_comments;
    }

    @Override
    protected void initView() {
        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);
        mAllcid = getIntent().getIntExtra("allcid", 0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mAllcommentsRlv.setLayoutManager(linearLayoutManager);
        mList = new ArrayList<>();
        mAdapter = new AllCommentsRlvAdapter(this, mList);
        mAllcommentsRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        mPresenter.getData(mAllcid,page);
    }

    @Override
    public void setData(AllCommentsBean bean) {
        showLoading();
        mList.addAll(bean.getResult().getReviews());
        mAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @OnClick(R.id.allcomments_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.allcomments_back:
                finish();
                break;
        }
    }
}

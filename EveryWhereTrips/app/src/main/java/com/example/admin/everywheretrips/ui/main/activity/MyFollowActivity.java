package com.example.admin.everywheretrips.ui.main.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.bean.MyFollowBean;
import com.example.admin.everywheretrips.presenter.MyFollowPresenter;
import com.example.admin.everywheretrips.ui.main.adapter.main.MyFollowRlvAdapter;
import com.example.admin.everywheretrips.view.main.MyFollowView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFollowActivity extends BaseActivity<MyFollowView, MyFollowPresenter> implements MyFollowView {

    @BindView(R.id.myfollow_rlv)
    RecyclerView mMyfollowRlv;
    @BindView(R.id.myfollw_back)
    ImageView mMyfollwBack;
    @BindView(R.id.myfollw_tb)
    Toolbar mMyfollwTb;
    private ArrayList<MyFollowBean.ResultBean.BanmiBean> mList;
    private MyFollowRlvAdapter mAdapter;
    private int page = 1;

    @Override
    protected MyFollowPresenter initPresenter() {
        return new MyFollowPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_follow;
    }

    @Override
    protected void initView() {
        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);
        mMyfollwTb.setTitle("");
        setSupportActionBar(mMyfollwTb);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMyfollowRlv.setLayoutManager(linearLayoutManager);
        mList = new ArrayList<>();
        mAdapter = new MyFollowRlvAdapter(this, mList);
        mMyfollowRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        mPresenter.getData(page);
    }

    @Override
    public void setData(MyFollowBean bean) {
        List<MyFollowBean.ResultBean.BanmiBean> banmi = bean.getResult().getBanmi();
        mList.addAll(banmi);
        mAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @OnClick(R.id.myfollw_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.myfollw_back:
                finish();
                break;
        }
    }
}

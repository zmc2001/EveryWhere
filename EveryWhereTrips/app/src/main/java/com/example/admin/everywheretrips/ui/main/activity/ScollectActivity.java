package com.example.admin.everywheretrips.ui.main.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.bean.YiCollectBean;
import com.example.admin.everywheretrips.presenter.FollwPresenter;
import com.example.admin.everywheretrips.ui.main.adapter.main.FollwAdapter;
import com.example.admin.everywheretrips.view.main.FollwView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ScollectActivity extends BaseActivity<FollwView, FollwPresenter> implements FollwView {

    @BindView(R.id.follw_rlv)
    RecyclerView mFollwRlv;
    @BindView(R.id.follw_back)
    ImageView mFollwBack;
    @BindView(R.id.follw_tb)
    Toolbar mFollwTb;
    private ArrayList<YiCollectBean.ResultBean.CollectedRoutesBean> mList;
    private FollwAdapter mAdapter;

    @Override
    protected FollwPresenter initPresenter() {
        return new FollwPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scollect;
    }

    @Override
    protected void initView() {
        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);

        mFollwTb.setTitle("");
        setSupportActionBar(mFollwTb);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mFollwRlv.setLayoutManager(linearLayoutManager);
        mList = new ArrayList<>();
        mAdapter = new FollwAdapter(this, mList);
        mFollwRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        mPresenter.getData();
    }

    @OnClick(R.id.follw_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.follw_back:
                finish();
                break;
        }
    }

    @Override
    public void setData(YiCollectBean bean) {
        final List<YiCollectBean.ResultBean.CollectedRoutesBean> list = bean.getResult().getCollectedRoutes();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        hideLoading();
    }
}

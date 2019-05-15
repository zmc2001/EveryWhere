package com.example.admin.everywheretrips.ui.main.fragment.mainfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseFragment;
import com.example.admin.everywheretrips.bean.Main_Home_ListBean;
import com.example.admin.everywheretrips.presenter.Main_Home_Presenter;
import com.example.admin.everywheretrips.ui.main.activity.HomeListOneActivity;
import com.example.admin.everywheretrips.ui.main.activity.HomeParticularsActivity;
import com.example.admin.everywheretrips.ui.main.adapter.main.MainHomeListRlvAdapter;
import com.example.admin.everywheretrips.util.SpUtil;
import com.example.admin.everywheretrips.view.main.Main_Home_View;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<Main_Home_View, Main_Home_Presenter>
        implements Main_Home_View {

    private static final String TAG = "HomeFragment";
    private int page = 1;
    @BindView(R.id.main_home_rlv)
    RecyclerView mMainHomeRlv;
    @BindView(R.id.main_home_srl)
    SmartRefreshLayout mMainHomeSrl;

    private ArrayList<Main_Home_ListBean.ResultBean.BannersBean> mBannersBeans;
    private ArrayList<Main_Home_ListBean.ResultBean.RoutesBean> mRoutesBeans;
    private MainHomeListRlvAdapter mAdapter;

    @Override
    protected Main_Home_Presenter initPresenter() {
        return new Main_Home_Presenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mMainHomeRlv.setLayoutManager(linearLayoutManager);
        mBannersBeans = new ArrayList<>();
        mRoutesBeans = new ArrayList<>();
        mAdapter = new MainHomeListRlvAdapter(getActivity(), mBannersBeans, mRoutesBeans);
        mMainHomeRlv.setAdapter(mAdapter);

        mAdapter.setMyOnClick(new MainHomeListRlvAdapter.MyOnClick() {
            @Override
            public void onClick(int position) {
                Object homepart = SpUtil.getParam("homepart", false);

                int cid = mRoutesBeans.get(position).getId();
                String price = mRoutesBeans.get(position).getPrice();
                //toastShort(price);
                Intent intent = new Intent(getActivity(), HomeParticularsActivity.class);
                intent.putExtra("cid",cid);
                intent.putExtra("price",price);
                startActivity(intent);
            }
        });

        mAdapter.setMyTwoOnClick(new MainHomeListRlvAdapter.MyTwoOnClick() {
            @Override
            public void onClick(int position) {
                String contentURL = mRoutesBeans.get(position).getContentURL();
                Intent intent = new Intent(getActivity(), HomeListOneActivity.class);
                intent.putExtra("homeimg",contentURL);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        showLoading();
        mPresenter.getData(page);
    }

    @Override
    public void setData(Main_Home_ListBean bean) {
        if (bean == null){
            showLoading();
        }else {
            List<Main_Home_ListBean.ResultBean.BannersBean> banners = bean.getResult().getBanners();
            List<Main_Home_ListBean.ResultBean.RoutesBean> routes = bean.getResult().getRoutes();
            mBannersBeans.addAll(banners);
            mRoutesBeans.addAll(routes);
            mAdapter.notifyDataSetChanged();
            hideLoading();
        }
    }

    @Override
    protected void initListener() {
        mMainHomeSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.getData(page);
                mMainHomeSrl.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mBannersBeans.clear();
                mRoutesBeans.clear();
                page=1;
                mPresenter.getData(page);
                mMainHomeSrl.finishRefresh();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

}

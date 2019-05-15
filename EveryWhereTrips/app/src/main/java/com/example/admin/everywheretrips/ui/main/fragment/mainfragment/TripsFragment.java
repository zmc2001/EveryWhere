package com.example.admin.everywheretrips.ui.main.fragment.mainfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseFragment;
import com.example.admin.everywheretrips.bean.BanmiFollowBean;
import com.example.admin.everywheretrips.bean.MainTripsListBean;
import com.example.admin.everywheretrips.bean.Main_Home_ListBean;
import com.example.admin.everywheretrips.net.BanmiServer;
import com.example.admin.everywheretrips.presenter.Main_Trips_Presenter;
import com.example.admin.everywheretrips.ui.main.activity.TripsParticularsActivity;
import com.example.admin.everywheretrips.ui.main.adapter.main.MainTripsRlvAdapter;
import com.example.admin.everywheretrips.util.SpUtil;
import com.example.admin.everywheretrips.util.ToastUtil;
import com.example.admin.everywheretrips.view.main.Main_Trips_View;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TripsFragment extends BaseFragment<Main_Trips_View, Main_Trips_Presenter>
        implements Main_Trips_View {

    @BindView(R.id.trips_rlv)
    RecyclerView mTripsRlv;
    @BindView(R.id.trips_srl)
    SmartRefreshLayout mTripsSrl;
    private int page = 1;
    private ArrayList<MainTripsListBean.ResultBean.BanmiBean> mList;
    private MainTripsRlvAdapter mAdapter;

    @Override
    protected Main_Trips_Presenter initPresenter() {
        return new Main_Trips_Presenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trips;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mTripsRlv.setLayoutManager(linearLayoutManager);
        mList = new ArrayList<>();
        mAdapter = new MainTripsRlvAdapter(getActivity(), mList);
        mTripsRlv.setAdapter(mAdapter);

        mAdapter.setMyOnClick(new MainTripsRlvAdapter.MyOnClick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), TripsParticularsActivity.class);
                int uid = mList.get(position).getId();
                SpUtil.setParam("tripscid",uid);
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
    public void onResume() {
        super.onResume();
        mList.clear();
        mPresenter.getData(page);
    }

    @Override
    public void setData(MainTripsListBean bean) {
        List<MainTripsListBean.ResultBean.BanmiBean> banmi = bean.getResult().getBanmi();
        mList.addAll(banmi);
        mAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    protected void initListener() {
        mTripsSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                mTripsSrl.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mList.clear();
                page=1;
                initData();
                mTripsSrl.finishRefresh();
            }
        });
    }

    public void unfollow(int cid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BanmiServer.banmiurl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        BanmiServer banmiServer = retrofit.create(BanmiServer.class);
        HashMap<String, String> map = new HashMap<>();
        String token = (String) SpUtil.getParam("token", "");
        map.put("banmi-app-token",token);
        Observable<BanmiFollowBean> getunfollow = banmiServer.getunfollow(cid, map);
        getunfollow.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanmiFollowBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(BanmiFollowBean banmiFollowBean) {
                        if (banmiFollowBean!=null){
                            if (banmiFollowBean.getCode()==0){
                                ToastUtil.showShort(banmiFollowBean.getResult().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtil.showShort(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void initfollow(int cid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BanmiServer.banmiurl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        BanmiServer banmiServer = retrofit.create(BanmiServer.class);
        HashMap<String, String> map = new HashMap<>();
        String token = (String) SpUtil.getParam("token", "");
        map.put("banmi-app-token",token);
        Observable<BanmiFollowBean> getfollow = banmiServer.getfollow(cid, map);
        getfollow.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanmiFollowBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(BanmiFollowBean banmiFollowBean) {
                        if (banmiFollowBean!=null){
                            if (banmiFollowBean.getCode()==0){
                                ToastUtil.showShort(banmiFollowBean.getResult().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtil.showShort(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

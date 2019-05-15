package com.example.admin.everywheretrips.ui.main.fragment.tripsparticfragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseFragment;
import com.example.admin.everywheretrips.bean.TripsCircuitBean;
import com.example.admin.everywheretrips.presenter.TripsCircuitPresenter;
import com.example.admin.everywheretrips.ui.main.adapter.main.TripsCircuitRlvAdapter;
import com.example.admin.everywheretrips.util.SpUtil;
import com.example.admin.everywheretrips.view.main.TripsCircuitView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CircuitFragment extends BaseFragment<TripsCircuitView, TripsCircuitPresenter>
        implements TripsCircuitView {

    @BindView(R.id.circuit_rlv)
    RecyclerView mCircuitRlv;
    Unbinder unbinder;
    private int mTripscid;
    private int page = 1;
    private ArrayList<TripsCircuitBean.ResultBean.RoutesBean> mList;
    private TripsCircuitRlvAdapter mAdapter;

    @Override
    protected TripsCircuitPresenter initPresenter() {
        return new TripsCircuitPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_circuit;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mCircuitRlv.setLayoutManager(linearLayoutManager);
        mTripscid = (int) SpUtil.getParam("tripscid", 0);
        mList = new ArrayList<>();
        mAdapter = new TripsCircuitRlvAdapter(getActivity(), mList);
        mCircuitRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        mPresenter.getData(mTripscid,page);
    }

    @Override
    public void setData(TripsCircuitBean bean) {
        List<TripsCircuitBean.ResultBean.RoutesBean> routes = bean.getResult().getRoutes();
        mList.addAll(routes);
        mAdapter.notifyDataSetChanged();
        hideLoading();
    }

}

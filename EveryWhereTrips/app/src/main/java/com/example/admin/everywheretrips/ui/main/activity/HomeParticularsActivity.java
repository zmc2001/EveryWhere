package com.example.admin.everywheretrips.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.bean.CircuitBean;
import com.example.admin.everywheretrips.bean.HomeParticularsBean;
import com.example.admin.everywheretrips.bean.YiCollectBean;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.MainServer;
import com.example.admin.everywheretrips.presenter.HomeParticularsPresenter;
import com.example.admin.everywheretrips.ui.main.adapter.main.HomePartiRlvAdapter;
import com.example.admin.everywheretrips.util.SpUtil;
import com.example.admin.everywheretrips.util.ToastUtil;
import com.example.admin.everywheretrips.view.main.HomeParticularsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeParticularsActivity extends BaseActivity<HomeParticularsView, HomeParticularsPresenter>
        implements HomeParticularsView {


    @BindView(R.id.home_part_rlv)
    RecyclerView mHomePartRlv;
    @BindView(R.id.homeparti_back)
    ImageView mHomepartiBack;
    @BindView(R.id.homeparti_fen)
    ImageView mHomepartiFen;
    @BindView(R.id.home_part_money)
    Button mHomePartMoney;
    private int mCid;
    private HomePartiRlvAdapter mAdapter;
    String token = (String) SpUtil.getParam("token", "");
    private int page = 1;

    @Override
    protected HomeParticularsPresenter initPresenter() {
        return new HomeParticularsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_particulars;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mCid = intent.getIntExtra("cid", 0);
        String money = intent.getStringExtra("price");
        //toastShort(money);
        mHomePartMoney.setText("￥"+money);
    }

    @Override
    protected void initData() {
        mPresenter.getData(mCid);
    }

    @Override
    public void setData(HomeParticularsBean bean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mHomePartRlv.setLayoutManager(linearLayoutManager);
        HomeParticularsBean.ResultBean result = bean.getResult();
        mAdapter = new HomePartiRlvAdapter(this, result);
        mHomePartRlv.setAdapter(mAdapter);

        mAdapter.setFourTvOnClick(new HomePartiRlvAdapter.FourTvOnClick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(HomeParticularsActivity.this, AllCommentsActivity.class);
                intent.putExtra("allcid",mCid);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.homeparti_back, R.id.homeparti_fen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeparti_back:
                finish();
                break;
            case R.id.homeparti_fen:
                break;
        }
    }

    public void initUnLike(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainServer.homesurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MainServer mainServer = retrofit.create(MainServer.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("banmi-app-token",token);
        Observable<CircuitBean> details = mainServer.getUnlike(id,map);
        details.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CircuitBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CircuitBean detailsBean) {
                        if (detailsBean!=null) {
                            if (detailsBean.getCode()==0) {
                                ToastUtil.showShort(detailsBean.getDesc());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void initCollect(int cid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainServer.homesurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MainServer mainServer = retrofit.create(MainServer.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("banmi-app-token",token);
        final Observable<CircuitBean> details = mainServer.getCircuit(cid, map);
        details.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CircuitBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CircuitBean detailsBean) {
                        if (detailsBean!=null) {
                            if (detailsBean.getCode()==0) {
                                ToastUtil.showShort(detailsBean.getDesc());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}

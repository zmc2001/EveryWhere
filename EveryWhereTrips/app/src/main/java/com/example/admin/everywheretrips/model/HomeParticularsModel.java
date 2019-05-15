package com.example.admin.everywheretrips.model;

import com.example.admin.everywheretrips.base.BaseModel;
import com.example.admin.everywheretrips.bean.HomeParticularsBean;
import com.example.admin.everywheretrips.bean.MainFollwBean;
import com.example.admin.everywheretrips.net.BaseObserver;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.MainServer;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.net.RxUtils;
import com.example.admin.everywheretrips.presenter.HomeParticularsPresenter;
import com.example.admin.everywheretrips.util.SpUtil;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HomeParticularsModel extends BaseModel {
    public void getData(int cid, final ResultCallBack<HomeParticularsBean> callBack){
        MainServer apiserver = HttpUtils.getInstance().getApiserver(MainServer.homesurl, MainServer.class);
        HashMap<String, String> map = new HashMap<>();
        String token = (String) SpUtil.getParam("token", "");
        map.put("banmi-app-token",token);
        Observable<HomeParticularsBean> homeParticulars = apiserver.getHomeParticulars(cid, map);
        homeParticulars.compose(RxUtils.<HomeParticularsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeParticularsBean>() {
                    @Override
                    public void onNext(HomeParticularsBean homeParticularsBean) {
                        callBack.onSuccess(homeParticularsBean);
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}

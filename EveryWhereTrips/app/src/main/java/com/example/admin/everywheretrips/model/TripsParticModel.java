package com.example.admin.everywheretrips.model;

import com.example.admin.everywheretrips.base.BaseModel;
import com.example.admin.everywheretrips.bean.TripsParticBean;
import com.example.admin.everywheretrips.net.BaseObserver;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.MainServer;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.net.RxUtils;
import com.example.admin.everywheretrips.util.SpUtil;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class TripsParticModel extends BaseModel{
    public void getData(int cid, int page, final ResultCallBack<TripsParticBean> callBack){
        MainServer apiserver = HttpUtils.getInstance().getApiserver(MainServer.homesurl, MainServer.class);
        HashMap<String, String> map = new HashMap<>();
        String token = (String) SpUtil.getParam("token", "sdsdw");
        map.put("banmi-app-token",token);
        Observable<TripsParticBean> tripsPartic = apiserver.getTripsPartic(cid, page, map);
        tripsPartic.compose(RxUtils.<TripsParticBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<TripsParticBean>() {
                    @Override
                    public void onNext(TripsParticBean tripsParticBean) {
                        callBack.onSuccess(tripsParticBean);
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

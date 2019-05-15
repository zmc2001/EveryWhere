package com.example.admin.everywheretrips.model;

import com.example.admin.everywheretrips.base.BaseModel;
import com.example.admin.everywheretrips.bean.TripsCircuitBean;
import com.example.admin.everywheretrips.net.BaseObserver;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.MainServer;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.net.RxUtils;
import com.example.admin.everywheretrips.util.SpUtil;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class TripsCircuitModel extends BaseModel {
    public void getData(int cid, int page, final ResultCallBack<TripsCircuitBean> callBack){
        MainServer apiserver = HttpUtils.getInstance().getApiserver(MainServer.homesurl, MainServer.class);
        HashMap<String, String> map = new HashMap<>();
        String token = (String) SpUtil.getParam("token", "");
        map.put("banmi-app-token",token);
        Observable<TripsCircuitBean> tripsCircuit = apiserver.getTripsCircuit(cid, page, map);
        tripsCircuit.compose(RxUtils.<TripsCircuitBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<TripsCircuitBean>() {
                    @Override
                    public void onNext(TripsCircuitBean tripsCircuitBean) {
                        callBack.onSuccess(tripsCircuitBean);
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

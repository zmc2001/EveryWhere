package com.example.admin.everywheretrips.model;

import com.example.admin.everywheretrips.base.BaseModel;
import com.example.admin.everywheretrips.bean.MainTripsListBean;
import com.example.admin.everywheretrips.net.BaseObserver;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.MainServer;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.net.RxUtils;
import com.example.admin.everywheretrips.util.SpUtil;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Main_Trips_Model extends BaseModel {
    public void getData(int page, final ResultCallBack<MainTripsListBean> callBack){
        MainServer apiserver = HttpUtils.getInstance().getApiserver(MainServer.homesurl, MainServer.class);
        HashMap<String, String> map = new HashMap<>();
        String token = (String) SpUtil.getParam("token", "as");
        map.put("banmi-app-token",token);
        Observable<MainTripsListBean> trips = apiserver.getTrips(page, map);
        trips.compose(RxUtils.<MainTripsListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MainTripsListBean>() {
                    @Override
                    public void onNext(MainTripsListBean mainTripsListBean) {
                        callBack.onSuccess(mainTripsListBean);
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

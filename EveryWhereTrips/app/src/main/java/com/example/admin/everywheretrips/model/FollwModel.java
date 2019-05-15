package com.example.admin.everywheretrips.model;

import com.example.admin.everywheretrips.base.BaseModel;
import com.example.admin.everywheretrips.bean.YiCollectBean;
import com.example.admin.everywheretrips.net.BaseObserver;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.MainServer;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.net.RxUtils;
import com.example.admin.everywheretrips.util.Logger;
import com.example.admin.everywheretrips.util.SpUtil;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class FollwModel extends BaseModel {
    public void getData(final ResultCallBack<YiCollectBean> callBack){
        MainServer apiserver = HttpUtils.getInstance().getApiserver(MainServer.homesurl, MainServer.class);
        String token = (String) SpUtil.getParam("token", "sds");
        Observable<YiCollectBean> yiCollect = apiserver.getYiCollect("xNYt6wXlaGwZTPzYp13SrUcIMmwuS9ASwCCF3GPID3AzQt6HB2OdklZmphWDyeXBGVF40tBf8l8CYVNDLRlaBY0y7T183EE7sMKasRNLJ6uPSH2a5TGtO2DaHhysbp9e3Q");
        yiCollect.compose(RxUtils.<YiCollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<YiCollectBean>() {
                    @Override
                    public void onNext(YiCollectBean bean) {
                        callBack.onSuccess(bean);
                        Logger.logD("adsd",bean.toString());
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

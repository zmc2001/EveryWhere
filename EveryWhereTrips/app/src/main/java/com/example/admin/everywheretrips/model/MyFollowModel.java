package com.example.admin.everywheretrips.model;

import com.example.admin.everywheretrips.base.BaseModel;
import com.example.admin.everywheretrips.bean.MyFollowBean;
import com.example.admin.everywheretrips.net.BanmiServer;
import com.example.admin.everywheretrips.net.BaseObserver;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.net.RxUtils;
import com.example.admin.everywheretrips.util.SpUtil;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MyFollowModel extends BaseModel {
    public void getData(int page, final ResultCallBack<MyFollowBean> callBack){
        BanmiServer apiserver = HttpUtils.getInstance().getApiserver(BanmiServer.banmiurl, BanmiServer.class);
        HashMap<String, String> map = new HashMap<>();
        String token = (String) SpUtil.getParam("token", "");
        map.put("banmi-app-token",token);
        Observable<MyFollowBean> myfollow = apiserver.getMyfollow(page, map);
        myfollow.compose(RxUtils.<MyFollowBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MyFollowBean>() {
                    @Override
                    public void onNext(MyFollowBean myFollowBean) {
                        callBack.onSuccess(myFollowBean);
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

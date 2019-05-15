package com.example.admin.everywheretrips.model;

import com.example.admin.everywheretrips.base.BaseModel;
import com.example.admin.everywheretrips.net.BaseObserver;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.MainServer;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.net.RxUtils;
import com.example.admin.everywheretrips.util.SpUtil;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class UpDateModel extends BaseModel {
    public void getData(String photo, String username,
                        String sex, String description,
                        final ResultCallBack<String> callBack){
        MainServer apiserver = HttpUtils.getInstance().getApiserver(MainServer.homesurl, MainServer.class);
        HashMap<String, String> map = new HashMap<>();
        String token = (String) SpUtil.getParam("token", "");
        map.put("banmi-app-token",token);
        Observable<ResponseBody> upDateInfo = apiserver.upDateInfo(photo, username, sex, description, map);
        upDateInfo.compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            callBack.onSuccess(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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

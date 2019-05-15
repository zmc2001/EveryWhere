package com.example.admin.everywheretrips.model;

import android.util.Log;

import com.example.admin.everywheretrips.base.BaseModel;
import com.example.admin.everywheretrips.bean.Main_Home_ListBean;
import com.example.admin.everywheretrips.net.BaseObserver;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.MainServer;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.net.RxUtils;
import com.example.admin.everywheretrips.util.SpUtil;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Main_Home_Model extends BaseModel {
    public void getData(int page,final ResultCallBack<Main_Home_ListBean> callBack){
        MainServer apiserver = HttpUtils.getInstance().getApiserver(MainServer.homesurl, MainServer.class);
        HashMap<String, String> map = new HashMap<>();
        String token = (String) SpUtil.getParam("token", "as");
        map.put("banmi-app-token",token);

        Observable<Main_Home_ListBean> data = apiserver.getData(page,map);

        data.compose(RxUtils.<Main_Home_ListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Main_Home_ListBean>() {
                    @Override
                    public void onNext(Main_Home_ListBean main_home_listBean) {
                        callBack.onSuccess(main_home_listBean);
                    }

                    @Override
                    public void error(String msg) {
                        //Log.i("tag",msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}

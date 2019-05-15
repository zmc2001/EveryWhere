package com.example.admin.everywheretrips.model;

import com.example.admin.everywheretrips.base.BaseModel;
import com.example.admin.everywheretrips.bean.AllCommentsBean;
import com.example.admin.everywheretrips.net.BaseObserver;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.MainServer;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.net.RxUtils;
import com.example.admin.everywheretrips.util.SpUtil;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class AllCommentsModel extends BaseModel {
    public void getData(int cid, int page, final ResultCallBack<AllCommentsBean> callBack){
        MainServer apiserver = HttpUtils.getInstance().getApiserver(MainServer.homesurl, MainServer.class);
        String token = (String) SpUtil.getParam("token", "");
        HashMap<String, String> map = new HashMap<>();
        map.put("banmi-app-token",token);
        Observable<AllCommentsBean> allComments = apiserver.getAllComments(cid, page, map);
        allComments.compose(RxUtils.<AllCommentsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AllCommentsBean>() {
                    @Override
                    public void onNext(AllCommentsBean allCommentsBean) {
                        callBack.onSuccess(allCommentsBean);
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

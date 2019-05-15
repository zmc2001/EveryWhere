package com.example.admin.everywheretrips.model;

import com.example.admin.everywheretrips.base.BaseModel;
import com.example.admin.everywheretrips.bean.LoginInfo;
import com.example.admin.everywheretrips.net.BaseObserver;
import com.example.admin.everywheretrips.net.EveryWhereService;
import com.example.admin.everywheretrips.net.HttpUtils;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.net.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class LoginOrBindModel extends BaseModel {
    public void loginSina(String uid, final ResultCallBack<LoginInfo> callBack){
        EveryWhereService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereService.BASE_URL, EveryWhereService.class);
        apiserver.postWeiboLogin(uid)
                .compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo != null){
                            if (loginInfo.getCode() == EveryWhereService.SUCCESS_CODE){
                                callBack.onSuccess(loginInfo);
                            }else {
                                callBack.onFail(loginInfo.getDesc());
                            }
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

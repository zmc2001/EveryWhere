package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseApp;
import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.VerifyCodeBean;
import com.example.admin.everywheretrips.model.LoginModel;
import com.example.admin.everywheretrips.net.ApiService;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.VerifyView;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class VerifyPresenter extends BasePresenter<VerifyView> {

    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginModel = new LoginModel();
        mModels.add(mLoginModel);
    }

    public void getData(){
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (bean!=null && bean.getCode()== ApiService.SUCCESS_CODE){
                    if (mMvpView!=null){
                        mMvpView.setData(bean.getData());
                    }
                }else {
                    if (mMvpView!=null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.get_verify_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

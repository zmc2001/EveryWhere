package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.VerifyCodeBean;
import com.example.admin.everywheretrips.model.LoginModel;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.LoginView;

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginModel = new LoginModel();
        mModels.add(mLoginModel);
    }

    public void getVerifyCode() {
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.model.UpDateModel;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.UpDateView;

public class UpDatePresenter extends BasePresenter<UpDateView> {

    private UpDateModel mUpDateModel;

    @Override
    protected void initModel() {
        mUpDateModel = new UpDateModel();
        mModels.add(mUpDateModel);
    }

    public void getData(String photo, String username,
                        String sex, String description){
        mUpDateModel.getData(photo, username, sex, description, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

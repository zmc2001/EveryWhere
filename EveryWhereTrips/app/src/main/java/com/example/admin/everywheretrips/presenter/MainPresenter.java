package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.MyInfoBean;
import com.example.admin.everywheretrips.model.MainModel;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.MainView;

public class MainPresenter extends BasePresenter<MainView> {

    private MainModel mMainModel;

    @Override
    protected void initModel() {
        mMainModel = new MainModel();
        mModels.add(mMainModel);
    }

    public void getData(){
        mMainModel.getData(new ResultCallBack<MyInfoBean>() {
            @Override
            public void onSuccess(MyInfoBean bean) {
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

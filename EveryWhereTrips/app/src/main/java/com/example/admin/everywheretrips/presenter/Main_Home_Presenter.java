package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.Main_Home_ListBean;
import com.example.admin.everywheretrips.model.Main_Home_Model;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.Main_Home_View;

public class Main_Home_Presenter extends BasePresenter<Main_Home_View> {

    private Main_Home_Model mMain_home_model;

    @Override
    protected void initModel() {
        mMain_home_model = new Main_Home_Model();
        mModels.add(mMain_home_model);
    }

    public void getData(int page){
        mMain_home_model.getData(page, new ResultCallBack<Main_Home_ListBean>() {
            @Override
            public void onSuccess(Main_Home_ListBean bean) {
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

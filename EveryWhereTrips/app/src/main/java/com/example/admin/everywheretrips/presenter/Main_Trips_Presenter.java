package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.MainTripsListBean;
import com.example.admin.everywheretrips.model.Main_Trips_Model;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.Main_Trips_View;

public class Main_Trips_Presenter extends BasePresenter<Main_Trips_View> {

    private Main_Trips_Model mMain_trips_model;

    @Override
    protected void initModel() {
        mMain_trips_model = new Main_Trips_Model();
        mModels.add(mMain_trips_model);
    }

    public void getData(int page){
        mMain_trips_model.getData(page, new ResultCallBack<MainTripsListBean>() {
            @Override
            public void onSuccess(MainTripsListBean bean) {
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

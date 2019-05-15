package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.TripsParticBean;
import com.example.admin.everywheretrips.model.TripsParticModel;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.TripsParticView;

public class TripsParticPresenter extends BasePresenter<TripsParticView> {

    private TripsParticModel mParticModel;

    @Override
    protected void initModel() {
        mParticModel = new TripsParticModel();
        mModels.add(mParticModel);
    }

    public void getData(int cid,int page){
        mParticModel.getData(cid, page, new ResultCallBack<TripsParticBean>() {
            @Override
            public void onSuccess(TripsParticBean bean) {
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

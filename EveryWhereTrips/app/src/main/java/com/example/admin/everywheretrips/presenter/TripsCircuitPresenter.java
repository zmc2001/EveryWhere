package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.TripsCircuitBean;
import com.example.admin.everywheretrips.model.TripsCircuitModel;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.TripsCircuitView;

public class TripsCircuitPresenter extends BasePresenter<TripsCircuitView> {

    private TripsCircuitModel mCircuitModel;

    @Override
    protected void initModel() {
        mCircuitModel = new TripsCircuitModel();
        mModels.add(mCircuitModel);
    }

    public void getData(int cid,int page){
        mCircuitModel.getData(cid, page, new ResultCallBack<TripsCircuitBean>() {
            @Override
            public void onSuccess(TripsCircuitBean bean) {
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

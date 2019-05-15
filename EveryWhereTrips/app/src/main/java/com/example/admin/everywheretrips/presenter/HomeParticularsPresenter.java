package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.HomeParticularsBean;
import com.example.admin.everywheretrips.model.HomeParticularsModel;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.HomeParticularsView;

public class HomeParticularsPresenter extends BasePresenter<HomeParticularsView> {

    private HomeParticularsModel mHomeParticularsModel;

    @Override
    protected void initModel() {
        mHomeParticularsModel = new HomeParticularsModel();
        mModels.add(mHomeParticularsModel);
    }

    public void getData(int cid){
        mHomeParticularsModel.getData(cid, new ResultCallBack<HomeParticularsBean>() {
            @Override
            public void onSuccess(HomeParticularsBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setData(bean);
                    }
                }else {
                    mMvpView.showLoading();
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

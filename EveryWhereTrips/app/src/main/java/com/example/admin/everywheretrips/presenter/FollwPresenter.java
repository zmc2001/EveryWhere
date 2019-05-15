package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.YiCollectBean;
import com.example.admin.everywheretrips.model.FollwModel;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.FollwView;

public class FollwPresenter extends BasePresenter<FollwView> {

    private FollwModel mFollwModel;

    @Override
    protected void initModel() {
        mFollwModel = new FollwModel();
        mModels.add(mFollwModel);
    }

    public void getData(){
        mFollwModel.getData(new ResultCallBack<YiCollectBean>() {
            @Override
            public void onSuccess(YiCollectBean bean) {
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

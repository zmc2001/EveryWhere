package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.MyFollowBean;
import com.example.admin.everywheretrips.model.MyFollowModel;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.MyFollowView;

public class MyFollowPresenter extends BasePresenter<MyFollowView> {

    private MyFollowModel mMyFollowModel;

    @Override
    protected void initModel() {
        mMyFollowModel = new MyFollowModel();
        mModels.add(mMyFollowModel);
    }

    public void getData(int page){
        mMyFollowModel.getData(page, new ResultCallBack<MyFollowBean>() {
            @Override
            public void onSuccess(MyFollowBean bean) {
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

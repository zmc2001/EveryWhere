package com.example.admin.everywheretrips.presenter;

import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.bean.AllCommentsBean;
import com.example.admin.everywheretrips.model.AllCommentsModel;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.view.main.AllCommentsView;

public class AllCommentsPresenter extends BasePresenter<AllCommentsView> {

    private AllCommentsModel mCommentsModel;

    @Override
    protected void initModel() {
        mCommentsModel = new AllCommentsModel();
        mModels.add(mCommentsModel);
    }

    public void getData(int cid,int page){
        mCommentsModel.getData(cid, page, new ResultCallBack<AllCommentsBean>() {
            @Override
            public void onSuccess(AllCommentsBean bean) {
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

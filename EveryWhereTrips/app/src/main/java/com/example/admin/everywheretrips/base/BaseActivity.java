package com.example.admin.everywheretrips.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.example.admin.everywheretrips.util.ToastUtil;
import com.example.admin.everywheretrips.widget.LoadingDialog;

import butterknife.ButterKnife;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 *         MVP
 *         V: 视图展示+用户交互
 *         P: V层和M层的桥梁+业务逻辑
 *         M: 负责数据处理:网络/文件/数据库...耗时操作
 */

public abstract class BaseActivity<V extends BaseView,P extends BasePresenter>
        extends AppCompatActivity  implements BaseView {

    //每次都要在子类中强转
    //protected BasePresenter mPresenter;
    protected P mPresenter;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (mPresenter != null){
            //可以强转,但是不对,但是这个基类的子类肯定会实现BaseMvpView或者他的子类
            //mPresenter.bind((BaseView) this);
            mPresenter.bind((V)this);
        }
        initView();
        initListener();
        initData();
    }

    protected abstract P initPresenter();

    protected void initListener() {

    }

    protected void initData() {

    }

    protected void initView(){

    };

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
        mPresenter = null;
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void toastShort(String string) {
        ToastUtil.showShort(string);
    }
}

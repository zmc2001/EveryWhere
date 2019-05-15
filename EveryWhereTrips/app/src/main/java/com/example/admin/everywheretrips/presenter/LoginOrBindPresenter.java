package com.example.admin.everywheretrips.presenter;

import android.util.Log;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseApp;
import com.example.admin.everywheretrips.base.BasePresenter;
import com.example.admin.everywheretrips.base.Constants;
import com.example.admin.everywheretrips.bean.LoginInfo;
import com.example.admin.everywheretrips.bean.VerifyCodeBean;
import com.example.admin.everywheretrips.model.LoginModel;
import com.example.admin.everywheretrips.model.LoginOrBindModel;
import com.example.admin.everywheretrips.net.ApiService;
import com.example.admin.everywheretrips.net.ResultCallBack;
import com.example.admin.everywheretrips.util.Logger;
import com.example.admin.everywheretrips.util.SpUtil;
import com.example.admin.everywheretrips.util.ToastUtil;
import com.example.admin.everywheretrips.view.main.LoginOrBindView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class LoginOrBindPresenter extends BasePresenter<LoginOrBindView> {

    private static final String TAG = "LoginOrBindPresenter";
    private LoginOrBindModel mLoginOrBindModel;
    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginOrBindModel = new LoginOrBindModel();
        mLoginModel = new LoginModel();
        mModels.add(mLoginOrBindModel);
    }

    public void oauthLogin(SHARE_MEDIA type){
        //UMShareAPI.get(mMvpView.getAct()).deleteOauth(mMvpView.getAct(),SHARE_MEDIA.SINA,authListener);
        UMShareAPI umShareAPI = UMShareAPI.get(mMvpView.getAct());
        //media,三方平台
        //authListener,登录回调
        umShareAPI.getPlatformInfo(mMvpView.getAct(), type, authListener);
    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (data != null){

                logMap(data);
                //login();
                //只写微博的,微信的成功不了
                if (platform == SHARE_MEDIA.SINA){
                    loginSina(data.get("uid"));
                }
                if (platform == SHARE_MEDIA.QQ){
                    loginSina(data.get("uid"));
                }
            }
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Logger.print(t.toString());
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_error));
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_cancel));
        }
    };

    private void loginSina(final String uid) {
        mLoginOrBindModel.loginSina(uid, new ResultCallBack<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo bean) {
                //登录成功了，需要做什么
                //1.跳转主页面
                //2.保存用户信息
                if (bean.getResult() != null){
                    saveUserInfo(bean.getResult());
                    if (mMvpView!=null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_success));
                        //ToastUtil.showShort(uid);
                        mMvpView.go2MainActivity();
                        //SpUtil.setParam("token",bean.getResult().getToken());
                        /*SpUtil.setParam("photo",bean.getResult().getPhoto());
                        SpUtil.setParam("username",bean.getResult().getUserName());
                        SpUtil.setParam("sex",bean.getResult().getGender());*/
                        //ToastUtil.showShort(bean.getResult().getToken());
                    }
                }else {
                    if (mMvpView != null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null){
                    mMvpView.toastShort(msg);
                }
            }
        });
    }

    private void saveUserInfo(LoginInfo.ResultBean result) {
        SpUtil.setParam("token",result.getToken());
        SpUtil.setParam(Constants.DESC,result.getDescription());
        SpUtil.setParam(Constants.USERNAME,result.getUserName());
        SpUtil.setParam(Constants.GENDER,result.getGender());
        SpUtil.setParam(Constants.EMAIL,result.getEmail());
        SpUtil.setParam(Constants.PHOTO,result.getPhoto());
        SpUtil.setParam(Constants.PHONE,result.getPhone());
    }

    private void logMap(Map<String, String> data) {
        for (Map.Entry<String, String> entry:data.entrySet()){
            Log.d(TAG, "logMap: "+entry.getKey()+","+entry.getValue());
        }
    }

    public void getVerifyCode(){
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (bean != null && bean.getCode() == ApiService.SUCCESS_CODE){
                    if (mMvpView != null){
                        mMvpView.setData(bean.getData());
                    }
                }else {
                    if (mMvpView != null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.get_verify_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

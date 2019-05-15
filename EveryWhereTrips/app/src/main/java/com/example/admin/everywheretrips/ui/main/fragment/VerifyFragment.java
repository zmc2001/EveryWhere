package com.example.admin.everywheretrips.ui.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseApp;
import com.example.admin.everywheretrips.base.BaseFragment;
import com.example.admin.everywheretrips.base.Constants;
import com.example.admin.everywheretrips.presenter.VerifyPresenter;
import com.example.admin.everywheretrips.ui.main.activity.LoginActivity;
import com.example.admin.everywheretrips.ui.main.activity.MainActivity;
import com.example.admin.everywheretrips.util.SpUtil;
import com.example.admin.everywheretrips.util.Tools;
import com.example.admin.everywheretrips.view.main.VerifyView;
import com.example.admin.everywheretrips.widget.IdentifyingCodeView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xts
 * Created by asus on 2019/5/4.
 */

public class VerifyFragment extends BaseFragment<VerifyView, VerifyPresenter> implements VerifyView {
    @BindView(R.id.iv_back)
    ImageView mIvback;
    @BindView(R.id.tv_send_again)
    TextView mTvSendAgain;
    @BindView(R.id.icv)
    IdentifyingCodeView mIcv;
    @BindView(R.id.tv_wait)
    TextView mTvWait;
    private int mTime;

    /**
     *
     * @param code 验证码,没有传递""
     * @return
     */
    public static VerifyFragment newIntence(String code){
        VerifyFragment verifyFragment = new VerifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VERIFY_CODE,code);
        verifyFragment.setArguments(bundle);
        return verifyFragment;
    }

    @Override
    protected VerifyPresenter initPresenter() {
        return new VerifyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify;
    }

    @OnClick({R.id.iv_back,R.id.tv_send_again})
    public void OnViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                backLoginOrBindFragment();
                break;
            case R.id.tv_send_again:
                //调用他是有条件的
                if (mTime == 0){
                    mPresenter.getData();
                    //重新发起倒计时
                    LoginOrBindFragment fragment = (LoginOrBindFragment) getActivity()
                            .getSupportFragmentManager()
                            .findFragmentByTag(LoginActivity.TAG);
                    fragment.countDown();
                }
                break;
        }
    }

    private void backLoginOrBindFragment() {
        Tools.closeKeyBoard(getActivity());
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment verify = manager.findFragmentByTag("verify");
        if (verify != null) {
            manager.beginTransaction().remove(verify).commit();
        }
    }

    @Override
    protected void initData() {
        //mPresenter.getData();
    }

    @Override
    public void setData(String data) {
        if (!TextUtils.isEmpty(data)) {
            mTvWait.setText(BaseApp.getRes().getString(R.string.verify_code)+data);
        }
    }

    @Override
    protected void initListener() {
        mIcv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }
        });
    }

    private void autoLogin() {
        if (mIcv.getTextContent().length() >= 4){
            //自动登录
            SpUtil.setParam("autologin",true);
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            toastShort(BaseApp.getRes().getString(R.string.auto_login));
            mIcv.setBackgroundEnter(false);
            mTvWait.setText(BaseApp.getRes().getString(R.string.wait_please));
            showLoading();
        }
    }

    public void setCountDownTime(int time){
        mTime = time;
        if (mTvSendAgain != null){
            if (time != 0){
                String format = String.format(getResources().getString(R.string.send_again) + "(%ss)", time);
                mTvSendAgain.setText(format);
                mTvSendAgain.setTextColor(getResources().getColor(R.color.c_999));
            }else {
                mTvSendAgain.setText(getResources().getString(R.string.send_again));
                mTvSendAgain.setTextColor(getResources().getColor(R.color.c_fa6a13));
            }
        }
    }

    @Override
    protected void initView() {
        boolean b = (boolean) SpUtil.getParam("autologin", false);
        if (b){
            startActivity(new Intent(getActivity(),MainActivity.class));
            getActivity().finish();
        }

        String code = getArguments().getString(Constants.VERIFY_CODE);
        setData(code);
    }
}

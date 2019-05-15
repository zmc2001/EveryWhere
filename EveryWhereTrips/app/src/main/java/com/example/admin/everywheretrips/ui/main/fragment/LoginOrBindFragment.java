package com.example.admin.everywheretrips.ui.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseFragment;
import com.example.admin.everywheretrips.base.Constants;
import com.example.admin.everywheretrips.presenter.LoginOrBindPresenter;
import com.example.admin.everywheretrips.ui.main.activity.LoginActivity;
import com.example.admin.everywheretrips.ui.main.activity.MainActivity;
import com.example.admin.everywheretrips.ui.main.activity.WebViewActivity;
import com.example.admin.everywheretrips.util.SpUtil;
import com.example.admin.everywheretrips.util.Tools;
import com.example.admin.everywheretrips.view.main.LoginOrBindView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class LoginOrBindFragment extends BaseFragment<LoginOrBindView, LoginOrBindPresenter>
        implements LoginOrBindView{

    private static final String TAG = "LoginOrBindFragment";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_hello)
    TextView mTvHello;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_coutry_code)
    TextView mTvCoutryCode;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.btn_send_verify)
    Button mBtnSendVerify;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.ll_or)
    LinearLayout mLlOr;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.iv_qq)
    ImageView mIvQq;
    @BindView(R.id.iv_sina)
    ImageView mIvSina;
    @BindView(R.id.tv_protocol)
    TextView mTvProtocol;
    @BindView(R.id.ll_oauthLogin)
    LinearLayout mLlOauthLogin;
    private UMShareAPI mUmShareAPI;
    private int mType;
    private static int COUNT_DOWN_TIME = 10;
    private int mTime = COUNT_DOWN_TIME;
    private Handler mHandler;
    //验证码
    private String mVerifyCode = "";
    private VerifyFragment mVerifyFragment;

    public static LoginOrBindFragment newIntance(int type){
        LoginOrBindFragment fragment = new LoginOrBindFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected LoginOrBindPresenter initPresenter() {
        return new LoginOrBindPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_bind;
    }

    @OnClick({R.id.iv_back, R.id.btn_send_verify, R.id.iv_wechat, R.id.iv_qq, R.id.iv_sina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.btn_send_verify:
                toast();
                getVerifyCode();
                addVerifyFragment();
                time();
                break;
            case R.id.iv_wechat:
                mPresenter.oauthLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.iv_qq:
                mPresenter.oauthLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.iv_sina:
                mPresenter.oauthLogin(SHARE_MEDIA.SINA);
                SpUtil.setParam("loginbind",true);
                break;
        }
    }

    private void toast() {
        if (TextUtils.isEmpty(mEtPhone.getText().toString())){
            toastShort("请输入手机号");
        }
    }

    private void time() {
        //避免多次执行倒计时
        if (mTime >0 && mTime < COUNT_DOWN_TIME){
            return;
        }
        countDown();
    }

    public void countDown() {
        if (mHandler == null){
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //避免倒计时变成负的
                if (mTime <= 0){
                    mTime = COUNT_DOWN_TIME;
                    return;
                }
                mTime--;
                if (mVerifyFragment != null){
                    mVerifyFragment.setCountDownTime(mTime);
                }
                countDown();
            }
        },1000);
    }

    private void getVerifyCode() {
        if (mTime >0 && mTime < COUNT_DOWN_TIME-1){
            //倒计时中
            return;
        }
        mVerifyCode="";
        mPresenter.getVerifyCode();
    }

    @Override
    protected void initView() {
        boolean b = (boolean) SpUtil.getParam("loginbind", false);
        if (b){
            startActivity(new Intent(getActivity(),MainActivity.class));
            getActivity().finish();
        }
        getArgumentsData();
        setProtocol();
        showOrHideView();
    }

    private void showOrHideView() {
        if (mType == LoginActivity.TYPE_LOGIN){
            //登录
            //View.VISIBLE 显示
            //View.INVISIBLE 隐藏,占位置
            //View.GONE 隐藏 不占位置
            mIvBack.setVisibility(View.INVISIBLE);
            mLlOauthLogin.setVisibility(View.VISIBLE);
            mLlOr.setVisibility(View.VISIBLE);
        }else {
            mIvBack.setVisibility(View.VISIBLE);
            mLlOauthLogin.setVisibility(View.GONE);
            mLlOr.setVisibility(View.GONE);
        }
    }

    private void getArgumentsData() {
        Bundle arguments = getArguments();
        mType = arguments.getInt(Constants.TYPE);
    }

    private void setProtocol() {
        SpannableString spanString = new SpannableString(getResources().getString(R.string.agree_protocol));
        //点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                //跳转webView页面，展示协议，但是webView有很多坑，所以我们不直接用
                WebViewActivity.startAct(getActivity());
            }
        };
        spanString.setSpan(clickableSpan,11,15,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spanString.setSpan(underlineSpan,11,15,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //前景色
        ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));
        spanString.setSpan(span, 11, 15, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //需要设置这个clickableSpan才会有效果
        mTvProtocol.setMovementMethod(LinkMovementMethod.getInstance());
        mTvProtocol.setText(spanString);
    }

    private void addVerifyFragment() {
        if (TextUtils.isEmpty(getPhone())){
            return;
        }

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //添加到回退栈
        fragmentTransaction.addToBackStack(null);

        mVerifyFragment = VerifyFragment.newIntence(mVerifyCode);

        //添加一个标识，在VerifyFragment取出，关闭VerifyFragment用
        fragmentTransaction.add(R.id.fl_container,mVerifyFragment,"verify").commit();
        //关闭软键盘
        Tools.closeKeyBoard(getActivity());
    }

    @Override
    protected void initListener() {
        //文本发生改变监听
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switchBtnState(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 根据输入框中是否有内容,切换发送验证码的背景
     * @param s
     */
    private void switchBtnState(CharSequence s) {
        if (TextUtils.isEmpty(s)){
            mBtnSendVerify.setBackgroundResource(R.drawable.bg_btn_ea_r15);
        }else {
            mBtnSendVerify.setBackgroundResource(R.drawable.bg_btn_fa6a13_r15);
        }

    }

    @Override
    public String getPhone() {
        return mEtPhone.getText().toString().trim();
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void go2MainActivity() {
        MainActivity.startAct(getContext());
        getActivity().finish();
    }

    @Override
    public void setData(String code) {
        this.mVerifyCode = code;
        if (mVerifyFragment != null){
            mVerifyFragment.setData(code);
        }
    }

}

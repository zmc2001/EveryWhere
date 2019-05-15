package com.example.admin.everywheretrips.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.base.BaseView;
import com.example.admin.everywheretrips.base.Constants;
import com.example.admin.everywheretrips.presenter.LoginPresenter;
import com.example.admin.everywheretrips.ui.main.fragment.LoginOrBindFragment;
import com.example.admin.everywheretrips.view.main.LoginView;
import com.umeng.socialize.UMShareAPI;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements BaseView {
    public static final int TYPE_LOGIN = 0;
    public static final int TYPE_BIND = 1;
    public static final String TAG = "loginFragment";
    private int mType;

    /**
     * 启动当前Activiy
     * @param context
     * @param type 如果是0:代表登录界面;1:代表要绑定手机
     */
    public static void startAct(Context context,int type){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        mPresenter.getVerifyCode();
    }

    @Override
    protected void initView() {
        getIntentData();
        FragmentManager manager = getSupportFragmentManager();
        LoginOrBindFragment fragment = LoginOrBindFragment.newIntance(mType);
        manager.beginTransaction().add(R.id.fl_container, fragment,TAG).commit();
    }

    private void getIntentData() {
        mType = getIntent().getIntExtra(Constants.TYPE, TYPE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏解决方案
        UMShareAPI.get(this).release();
    }

}

package com.example.admin.everywheretrips.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.presenter.EmptyPresenter;
import com.example.admin.everywheretrips.view.main.EmptyView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpUserNameActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.update_name_back)
    ImageView mUpdateNameBack;
    @BindView(R.id.upname_tb_ok)
    TextView mUpphoteTbOk;
    @BindView(R.id.upname_et)
    EditText mUpnameEt;
    @BindView(R.id.upname_num)
    TextView mUpnameNum;
    @BindView(R.id.upname_tb)
    Toolbar mUpnameTb;
    private Intent mIntent;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_up_user_name;
    }

    @Override
    protected void initView() {
        //光标移动到最后
        mUpnameEt.setSelection(mUpnameEt.getText().length());

        mUpnameTb.setTitle("");
        setSupportActionBar(mUpnameTb);

        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);

        //取出原本的用户名
        mIntent = getIntent();
        String name = mIntent.getStringExtra("name");
        mUpnameEt.setText(name);

        int length = mUpnameEt.length();
        mUpnameNum.setText(length+"/10");

    }

    @OnClick({R.id.update_name_back, R.id.upname_tb_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.update_name_back:
                finish();
                break;
            case R.id.upname_tb_ok:
                upName();
                break;
        }
    }

    private void upName() {
        String s = mUpnameEt.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("upname", s);
        setResult(22, intent);
        finish();
    }

    @Override
    protected void initListener() {
        mUpnameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = mUpnameEt.length();
                mUpnameNum.setText(length+"/10");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}

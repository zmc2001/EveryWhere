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

public class UpAutographActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.update_autograph_back)
    ImageView mUpdateAutographBack;
    @BindView(R.id.upautograph_tb_ok)
    TextView mUpautographTbOk;
    @BindView(R.id.upautograph_tb)
    Toolbar mUpautographTb;
    @BindView(R.id.upautograph_et)
    EditText mUpautographEt;
    @BindView(R.id.upautograph_num)
    TextView mUpautographNum;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_up_autograph;
    }

    @Override
    protected void initView() {
        //将光标移动到最后
        mUpautographEt.setSelection(mUpautographEt.getText().length());

        mUpautographTb.setTitle("");
        setSupportActionBar(mUpautographTb);

        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);

        Intent intent = getIntent();
        String autograph = intent.getStringExtra("autograph");
        mUpautographEt.setText(autograph);

        int length = mUpautographEt.length();
        mUpautographNum.setText(length + "/10");
    }

    @OnClick({R.id.update_autograph_back, R.id.upautograph_tb_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.update_autograph_back:
                finish();
                break;
            case R.id.upautograph_tb_ok:
                upAutograph();
                break;
        }
    }

    private void upAutograph() {
        String s = mUpautographEt.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("upautograph", s);
        setResult(44, intent);
        finish();
    }

    @Override
    protected void initListener() {
        mUpautographEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = mUpautographEt.length();
                mUpautographNum.setText(length + "/10");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

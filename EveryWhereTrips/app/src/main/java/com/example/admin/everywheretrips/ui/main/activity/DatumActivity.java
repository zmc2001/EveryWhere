package com.example.admin.everywheretrips.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.presenter.EmptyPresenter;
import com.example.admin.everywheretrips.presenter.UpDatePresenter;
import com.example.admin.everywheretrips.util.ImageLoader;
import com.example.admin.everywheretrips.util.SpUtil;
import com.example.admin.everywheretrips.view.main.EmptyView;
import com.example.admin.everywheretrips.view.main.UpDateView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatumActivity extends BaseActivity<UpDateView, UpDatePresenter>
        implements UpDateView, View.OnClickListener {

    @BindView(R.id.datum_tb)
    Toolbar mDatumTb;
    @BindView(R.id.datum_head_photo)
    RelativeLayout mDatumHeadPhoto;
    @BindView(R.id.datum_name)
    RelativeLayout mDatumName;
    @BindView(R.id.datum_sex)
    RelativeLayout mDatumSex;
    @BindView(R.id.datum_autograph)
    RelativeLayout mDatumAutograph;
    @BindView(R.id.datum_back)
    ImageView mDatumBack;
    @BindView(R.id.datum_head_photoo)
    ImageView mDatumHeadPhotoo;
    @BindView(R.id.datum_namee)
    TextView mDatumNamee;
    @BindView(R.id.datum_sexx)
    TextView mDatumSexx;
    @BindView(R.id.datum_autographh)
    TextView mDatumAutographh;
    private PopupWindow mPopupWindow;
    private View mInflate;
    private Button mUpdate_sex_man;
    private Button mUpdate_sex_girl;
    private Button mUpdate_sex_yao;
    private int NAME_CODE = 11;
    private int AUTOGRAPH_CODE = 33;
    private String mImageurl;
    private String mUpname;
    private String mUpautograph;
    private String mMPhoto;

    @Override
    protected UpDatePresenter initPresenter() {
        return new UpDatePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_datum;
    }

    @Override
    protected void initView() {
        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);
        mDatumTb.setTitle("");
        setSupportActionBar(mDatumTb);

        String mUsername = (String) SpUtil.getParam("userName", "");
        String mSex = (String) SpUtil.getParam("usersex", "");
        mMPhoto = (String) SpUtil.getParam("userphoto", "");
        String mUserdescription = (String) SpUtil.getParam("userdescription", "");
        mDatumNamee.setText(mUsername);
        mDatumSexx.setText(mSex);
        mDatumAutographh.setText(mUserdescription);
        //ImageLoader.setImage(this,photo,mDatumHeadPhotoo,R.mipmap.ic_launcher);
        ImageLoader.setCircleImage(this, mMPhoto,mDatumHeadPhotoo,R.mipmap.ic_launcher);

    }

    @OnClick({R.id.datum_head_photo, R.id.datum_name, R.id.datum_sex, R.id.datum_autograph, R.id.datum_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.datum_head_photo:
                updatePhoto();
                break;
            case R.id.datum_name:
                openUpdateName();
                break;
            case R.id.datum_sex:
                openPop();
                break;
            case R.id.datum_autograph:
                openUpdateAutograph();
                break;
            case R.id.datum_back:
                finish();
                break;
        }
    }

    private void openUpdateAutograph() {
        String autograph = mDatumAutographh.getText().toString();
        Intent intent = new Intent(this, UpAutographActivity.class);
        intent.putExtra("autograph", autograph);
        startActivityForResult(intent, 33);
    }

    private void openUpdateName() {
        String name = mDatumNamee.getText().toString();
        Intent intent = new Intent(this, UpUserNameActivity.class);
        intent.putExtra("name", name);
        startActivityForResult(intent, 11);
    }

    private void openPop() {
        mInflate = LayoutInflater.from(this).inflate(R.layout.update_sex_pop, null);
        mPopupWindow = new PopupWindow(mInflate,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout update_sex_ll = mInflate.findViewById(R.id.update_sex_ll);
        //点击外部消失
        mPopupWindow.setBackgroundDrawable(null);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(update_sex_ll, Gravity.CENTER, 0, 0);
        mInflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mUpdate_sex_man = mInflate.findViewById(R.id.update_sex_man);
        mUpdate_sex_girl = mInflate.findViewById(R.id.update_sex_girl);
        mUpdate_sex_yao = mInflate.findViewById(R.id.update_sex_yao);
        mUpdate_sex_man.setOnClickListener(this);
        mUpdate_sex_girl.setOnClickListener(this);
        mUpdate_sex_yao.setOnClickListener(this);
    }

    private void updatePhoto() {
        Intent intent = new Intent(this, UpPhotoActivity.class);
        intent.putExtra("oldphoto",mMPhoto);
        startActivityForResult(intent, 12);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_sex_man:
                String man = mUpdate_sex_man.getText().toString();
                mDatumSexx.setText(man);
                mPopupWindow.dismiss();
                updateinfo();
                break;
            case R.id.update_sex_girl:
                String girl = mUpdate_sex_girl.getText().toString();
                mDatumSexx.setText(girl);
                mPopupWindow.dismiss();
                updateinfo();
                break;
            case R.id.update_sex_yao:
                String yao = mUpdate_sex_yao.getText().toString();
                mDatumSexx.setText(yao);
                mPopupWindow.dismiss();
                updateinfo();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == 22) {
            mUpname = data.getStringExtra("upname");
            mDatumNamee.setText(mUpname);
        }
        if (requestCode == 33 && resultCode == 44) {
            mUpautograph = data.getStringExtra("upautograph");
            mDatumAutographh.setText(mUpautograph);
        }
        if (requestCode == 12 && resultCode == 13) {
            mImageurl = data.getStringExtra("imageurl");
            toastShort(mImageurl);
            RequestOptions requestOptions = new RequestOptions().circleCrop();
            Glide.with(this).load(mImageurl).apply(requestOptions).into(mDatumHeadPhotoo);
        }
        toastShort("修改成功");
        updateinfo();
    }

    private void updateinfo() {
        mPresenter.getData(mImageurl,mDatumNamee.getText().toString(),
                mDatumSexx.getText().toString(),mDatumAutographh.getText().toString());
    }

    @Override
    public void setData(String str) {
        /*SpUtil.setParam("userphoto",mImageurl);
        SpUtil.setParam("userName",mUpname);
        SpUtil.setParam("usersex",mDatumSexx.getText());
        SpUtil.setParam("userdescription",mUpautograph);*/
    }
}

package com.example.admin.everywheretrips.ui.main.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.bean.TripsParticBean;
import com.example.admin.everywheretrips.presenter.TripsParticPresenter;
import com.example.admin.everywheretrips.ui.main.adapter.main.MainTripsRlvAdapter;
import com.example.admin.everywheretrips.ui.main.adapter.main.TripsCircuitRlvAdapter;
import com.example.admin.everywheretrips.ui.main.adapter.main.TripsParticPageAdapter;
import com.example.admin.everywheretrips.ui.main.fragment.mainfragment.TripsFragment;
import com.example.admin.everywheretrips.ui.main.fragment.tripsparticfragment.CircuitFragment;
import com.example.admin.everywheretrips.ui.main.fragment.tripsparticfragment.DynamicFragment;
import com.example.admin.everywheretrips.util.ImageLoader;
import com.example.admin.everywheretrips.util.SpUtil;
import com.example.admin.everywheretrips.view.main.TripsParticView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TripsParticularsActivity extends BaseActivity<TripsParticView, TripsParticPresenter>
        implements TripsParticView {

    @BindView(R.id.tripspartic_back)
    ImageView mTripsparticBack;
    @BindView(R.id.tripspartic_fen)
    ImageView mTripsparticFen;
    @BindView(R.id.tripspartic_tb)
    Toolbar mTripsparticTb;
    @BindView(R.id.tripspartic_photo)
    ImageView mTripsparticPhoto;
    @BindView(R.id.tripspartic_attention)
    TextView mTripsparticAttention;
    @BindView(R.id.tripspartic_name)
    TextView mTripsparticName;
    @BindView(R.id.tripspartic_follw)
    ImageView mTripsparticFollw;
    @BindView(R.id.tripspartic_follw_tv)
    TextView mTripsparticFollwTv;
    @BindView(R.id.tripspartic_address)
    TextView mTripsparticAddress;
    @BindView(R.id.tripspartic_occupation)
    TextView mTripsparticOccupation;
    @BindView(R.id.tripspartic_content)
    TextView mTripsparticContent;
    @BindView(R.id.tripspartic_tab)
    TabLayout mTripsparticTab;
    @BindView(R.id.tripspartic_vp)
    ViewPager mTripsparticVp;
    private int mTripsuid;
    private int page = 1;

    @Override
    protected TripsParticPresenter initPresenter() {
        return new TripsParticPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_trips_particulars;
    }

    @OnClick({R.id.tripspartic_back, R.id.tripspartic_fen, R.id.tripspartic_follw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tripspartic_back:
                finish();
                break;
            case R.id.tripspartic_fen:
                break;
            case R.id.tripspartic_follw:
                getfollow();
                break;
        }
    }

    private void getfollow() {
        TripsFragment tripsFragment = new TripsFragment();
        boolean tag = (boolean) mTripsparticFollw.getTag();
        if (!tag){
            tripsFragment.initfollow(mTripsuid);
            mTripsparticFollw.setImageResource(R.mipmap.follow);
            mTripsparticFollwTv.setText("已关注");
        }else {
            tripsFragment.unfollow(mTripsuid);
            mTripsparticFollw.setImageResource(R.mipmap.follow_unselected);
            mTripsparticFollwTv.setText("关注");
        }
    }

    @Override
    protected void initView() {
        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);
        mTripsuid = (int) SpUtil.getParam("tripscid",0);
        mTripsparticTb.setTitle("");
        setSupportActionBar(mTripsparticTb);

        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        fragments.add(new DynamicFragment());
        fragments.add(new CircuitFragment());
        titles.add("动态");
        titles.add("线路");
        TripsParticPageAdapter adapter = new TripsParticPageAdapter(getSupportFragmentManager(), fragments, titles);
        mTripsparticVp.setAdapter(adapter);
        mTripsparticTab.setupWithViewPager(mTripsparticVp);
    }

    @Override
    protected void initData() {
        showLoading();
        mPresenter.getData(mTripsuid,page);
    }

    @Override
    public void setData(TripsParticBean bean) {
        if (bean.getResult().getBanmi().isIsFollowed()){
            mTripsparticFollw.setImageResource(R.mipmap.follow);
            mTripsparticFollwTv.setText("已关注");
        }else {
            mTripsparticFollw.setImageResource(R.mipmap.follow_unselected);
            mTripsparticFollwTv.setText("关注");
        }
        mTripsparticFollw.setTag(bean.getResult().getBanmi().isIsFollowed());
        TripsParticBean.ResultBean.BanmiBean banmi = bean.getResult().getBanmi();
        ImageLoader.setImage(this,banmi.getPhoto(),mTripsparticPhoto,R.drawable.icon_me_kaquan_banmi2);
        mTripsparticName.setText(banmi.getName());
        mTripsparticAttention.setText(banmi.getFollowing()+"人关注");
        mTripsparticAddress.setText(banmi.getLocation());
        mTripsparticOccupation.setText(banmi.getOccupation());
        mTripsparticContent.setText(banmi.getIntroduction());
        hideLoading();
    }
}

package com.example.admin.everywheretrips.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.bean.MyInfoBean;
import com.example.admin.everywheretrips.presenter.MainPresenter;
import com.example.admin.everywheretrips.ui.main.adapter.main.MainPageAdapter;
import com.example.admin.everywheretrips.ui.main.fragment.mainfragment.HomeFragment;
import com.example.admin.everywheretrips.ui.main.fragment.mainfragment.TripsFragment;
import com.example.admin.everywheretrips.util.ImageLoader;
import com.example.admin.everywheretrips.util.SpUtil;
import com.example.admin.everywheretrips.view.main.MainView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @BindView(R.id.main_tb_img)
    ImageView mMainTbImg;
    @BindView(R.id.main_tb)
    Toolbar mMainTb;
    @BindView(R.id.main_vp)
    ViewPager mMainVp;
    @BindView(R.id.main_tab)
    TabLayout mMainTab;
    @BindView(R.id.messgae)
    ImageView mMessgae;
    @BindView(R.id.main_nav)
    NavigationView mMainNav;
    @BindView(R.id.main_dl)
    DrawerLayout mMainDl;
    private RelativeLayout mNavHeadPhoto;
    private ImageView mNavHeadPhotoo;
    private RelativeLayout mNav_head_follw;
    private TextView mNav_head_name;
    private TextView mNav_head_signname;
    private RelativeLayout mNav_head_myfollow;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        String name = "的急啊离开酒店";

        StatusBarUtil.setLightMode(this);

        mMainTb.setTitle("");
        setSupportActionBar(mMainTb);

        addfragment();

        addhead();
    }

    private void addhead() {
        View headerView = mMainNav.getHeaderView(0);
        mNavHeadPhoto = headerView.findViewById(R.id.nav_head_photo);
        mNavHeadPhotoo = headerView.findViewById(R.id.nav_head_photoo);
        mNav_head_follw = headerView.findViewById(R.id.nav_head_follw);
        mNav_head_name = headerView.findViewById(R.id.nav_head_name);
        mNav_head_signname = headerView.findViewById(R.id.nav_head_signname);
        mNav_head_myfollow = headerView.findViewById(R.id.nav_head_myfollow);

        //Glide.with(this).load(R.drawable.icon_me_kaquan_banmi2).into(mNavHeadPhotoo);
    }

    private void addfragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TripsFragment());

        mMainTab.addTab(mMainTab.newTab().setText("首页").setIcon(R.drawable.main_home_selector));
        mMainTab.addTab(mMainTab.newTab().setText("伴米").setIcon(R.drawable.main_trips_selector));
        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        mMainVp.setAdapter(adapter);
        mMainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mMainVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mMainVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mMainTab));
    }

    @Override
    protected void initListener() {
        mMainTbImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainDl.openDrawer(Gravity.LEFT);
            }
        });

        mNavHeadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DatumActivity.class));
                mMainDl.closeDrawer(mMainNav);
            }
        });

        mNav_head_follw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ScollectActivity.class));
                mMainDl.closeDrawer(mMainNav);
            }
        });

        mNav_head_myfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MyFollowActivity.class));
                mMainDl.closeDrawer(mMainNav);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.getData();
    }

    @Override
    public void setData(MyInfoBean bean) {
        ImageLoader.setCircleImage(this,bean.getResult().getPhoto()
                ,mMainTbImg,R.drawable.begin_page);
        ImageLoader.setCircleImage(this,bean.getResult().getPhoto()
                ,mNavHeadPhotoo,R.drawable.begin_page);
        mNav_head_name.setText(bean.getResult().getUserName());
        mNav_head_signname.setText(bean.getResult().getDescription());

        SpUtil.setParam("userphoto",bean.getResult().getPhoto());
        SpUtil.setParam("userName",bean.getResult().getUserName());
        SpUtil.setParam("usersex",bean.getResult().getGender());
        SpUtil.setParam("userdescription",bean.getResult().getDescription());
    }
}

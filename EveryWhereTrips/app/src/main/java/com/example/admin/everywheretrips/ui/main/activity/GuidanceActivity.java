package com.example.admin.everywheretrips.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.ui.main.adapter.main.GuidancePageAdapter;
import com.example.admin.everywheretrips.util.PreviewIndicator;
import com.example.admin.everywheretrips.util.SpUtil;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

public class GuidanceActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager mGuidanceVp;
    private PreviewIndicator mGuidePi;
    /**
     * 立即体验
     */
    private Button mGuideBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);//还原回正常的Theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);
        initView();
        initData();
    }

    private void initData() {
        mGuidePi.initSize(80, 32, 6);
        mGuidePi.setNumbers(3);
        mGuidanceVp.addOnPageChangeListener(this);
    }

    private void initView() {
        boolean b = (boolean) SpUtil.getParam("guide", false);
        if (b) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        /*
        * mIndicator.setSelected(i);
        if (i == 2) {
            mBtnBegin.setVisibility(View.VISIBLE);
            mIndicator.setVisibility(View.GONE);
        } else {
            mBtnBegin.setVisibility(View.GONE);
            mIndicator.setVisibility(View.VISIBLE);
        }
        * */

        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);
        mGuidePi = (PreviewIndicator) findViewById(R.id.guide_pi);
        mGuidanceVp = (ViewPager) findViewById(R.id.guidance_vp);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.guide_01);
        list.add(R.drawable.guide_02);
        list.add(R.drawable.guide_03);

        GuidancePageAdapter adapter = new GuidancePageAdapter(this, list);
        mGuidanceVp.setAdapter(adapter);

        mGuideBtn = (Button) findViewById(R.id.guide_btn);
        mGuideBtn.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mGuidePi.setSelected(i);
        if (i==0){
            mGuideBtn.setVisibility(View.GONE);
            mGuidePi.setVisibility(View.VISIBLE);
        }else if (i==1){
            mGuideBtn.setVisibility(View.GONE);
            mGuidePi.setVisibility(View.VISIBLE);
        } else if (i == 2) {
            mGuidePi.setVisibility(View.GONE);
            mGuideBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.guide_btn:
                mGuideBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SpUtil.setParam("guide",true);
                        startActivity(new Intent(GuidanceActivity.this, LoginActivity.class));
                        finish();
                    }
                });
                break;
        }
    }
}

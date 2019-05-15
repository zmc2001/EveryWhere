package com.example.admin.everywheretrips.ui.main.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.widget.AndroidJs;
import com.jaeger.library.StatusBarUtil;

public class HomeListOneActivity extends AppCompatActivity {

    private WebView mHomeListoneWb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list_one);
        initView();
    }

    private void initView() {
        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);

        mHomeListoneWb = (WebView) findViewById(R.id.home_listone_wb);
        String homeimg = getIntent().getStringExtra("homeimg")+"?os=android";
        WebSettings settings = mHomeListoneWb.getSettings();
        settings.setJavaScriptEnabled(true);
        mHomeListoneWb.setWebViewClient(new WebViewClient());
        mHomeListoneWb.loadUrl(homeimg);

        AndroidJs androidJs = new AndroidJs(this);
        mHomeListoneWb.addJavascriptInterface(androidJs,"android");
    }
}

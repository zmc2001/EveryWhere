package com.example.admin.everywheretrips.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.presenter.EmptyPresenter;
import com.example.admin.everywheretrips.view.main.EmptyView;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.webview_tb_title)
    TextView mWebviewTbTitle;
    @BindView(R.id.webview_tb)
    Toolbar webviewTb;
    @BindView(R.id.webview_ll)
    LinearLayout webviewLl;
    @BindView(R.id.webview_back)
    ImageView mWebviewBack;
    private AgentWeb mAgentWeb;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);

        webviewTb.setTitle("");
        setSupportActionBar(webviewTb);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(webviewLl, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .createAgentWeb()
                .ready()
                .go("https://api.banmi.com/app2017/agreement.html");

        mAgentWeb.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)) {
                    mWebviewTbTitle.setText(title);
                }
                super.onReceivedTitle(view, title);
            }
        });
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.webview_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.webview_back:
                finish();
                break;
        }
    }
}

package com.example.admin.everywheretrips.ui.main.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.everywheretrips.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        startActivity(new Intent(SplashActivity.this,GuidanceActivity.class));
        finish();
    }
}

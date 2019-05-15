package com.example.admin.everywheretrips.widget;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.example.admin.everywheretrips.ui.main.activity.HomeParticularsActivity;
import com.example.admin.everywheretrips.ui.main.activity.MainActivity;

/**
 * Created by $sl on 2019/5/15 16:29.
 */
public class AndroidJs extends Object {
    private Context mContext;

    public AndroidJs(Context context) {
        mContext = context;
    }

    private static final String TAG = "AndroidJs----";

    @JavascriptInterface
    public void callAndroid(String msg, int id) {
        if (msg.equals("route_details")) {
            Log.d(TAG, "callAndroid: " + id);
            Intent intent = new Intent(mContext, HomeParticularsActivity.class);
            intent.putExtra("cid", id);
            mContext.startActivity(intent);
        }
    }

    @JavascriptInterface
    public void callAndroid(String msg) {
        if (msg.equals("subject_list")) {
            Log.d(TAG, "callAndroid: " + 1231);
            mContext.startActivity(new Intent(mContext, MainActivity.class));
        }
    }
}

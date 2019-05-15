package com.example.admin.everywheretrips.view.main;

import android.app.Activity;

import com.example.admin.everywheretrips.base.BaseView;

/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public interface LoginOrBindView extends BaseView {
    String getPhone();
    Activity getAct();
    void go2MainActivity();

    void setData(String code);
}

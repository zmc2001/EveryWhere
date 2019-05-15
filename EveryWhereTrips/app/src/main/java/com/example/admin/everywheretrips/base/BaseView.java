package com.example.admin.everywheretrips.base;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface BaseView {
    //显示加载loading的方法
    void showLoading();
    //隐藏加载loading的方法
    void hideLoading();

    void toastShort(String string);
}

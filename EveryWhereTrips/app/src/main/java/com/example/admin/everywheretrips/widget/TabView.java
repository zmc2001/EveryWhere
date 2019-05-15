package com.example.admin.everywheretrips.widget;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseApp;

public class TabView extends TabLayout{
    public TabView(Context context) {
        super(context);
    }

    public View getTabView(String title, int image_src) {
        View v = LayoutInflater.from(BaseApp.getInstance()).inflate(R.layout.tab_item_view, null);
        TextView textView = (TextView) v.findViewById(R.id.textview);
        textView.setText(title);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageview);
        imageView.setImageResource(image_src);
        return v;
    }
}

package com.example.admin.everywheretrips.ui.main.adapter.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseApp;
import com.example.admin.everywheretrips.ui.main.activity.LoginActivity;
import com.example.admin.everywheretrips.util.PreviewIndicator;
import com.example.admin.everywheretrips.util.SpUtil;

import java.util.ArrayList;

public class GuidancePageAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<Integer> mList;
    private TextView mText1;
    private TextView mText2;
    private TextView mText3;

    public GuidancePageAdapter(Context context, ArrayList<Integer> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = View.inflate(mContext, R.layout.item_guidance_page, null);
        ImageView img = inflate.findViewById(R.id.item_guideance_img);
        mText1 = inflate.findViewById(R.id.guide_textone);
        mText2 = inflate.findViewById(R.id.guide_texttwo);
        mText3 = inflate.findViewById(R.id.guide_textthree);
        if (position == mList.size()-3){
            mText1.setVisibility(View.VISIBLE);
            mText2.setVisibility(View.GONE);
            mText3.setVisibility(View.GONE);
            setProtocolOne();
        }else if (position == mList.size()-2){
            mText1.setVisibility(View.GONE);
            mText2.setVisibility(View.VISIBLE);
            mText3.setVisibility(View.GONE);
            setProtocolTwo();
        }else if (position == mList.size()-1){
            mText3.setVisibility(View.VISIBLE);
            mText1.setVisibility(View.GONE);
            mText2.setVisibility(View.GONE);
            setProtocolThree();

        }
        img.setImageResource(mList.get(position));
        container.addView(inflate);
        return inflate;
    }

    private void setProtocolTwo() {
        SpannableString spanString = new SpannableString(BaseApp.getRes().getString(R.string.guide_texttwo));
        //前景色
        ForegroundColorSpan span2 = new ForegroundColorSpan(BaseApp.getRes().getColor(R.color.c_fa6a13));
        spanString.setSpan(span2, 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ForegroundColorSpan span = new ForegroundColorSpan(BaseApp.getRes().getColor(R.color.c_78d9ff));
        spanString.setSpan(span, 4, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //需要设置这个clickableSpan才会有效果
        mText2.setMovementMethod(LinkMovementMethod.getInstance());
        mText2.setText(spanString);
    }

    private void setProtocolThree() {
        SpannableString spanString = new SpannableString(BaseApp.getRes().getString(R.string.guide_textthree));
        //前景色
        ForegroundColorSpan span2 = new ForegroundColorSpan(BaseApp.getRes().getColor(R.color.c_fa6a13));
        spanString.setSpan(span2, 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ForegroundColorSpan span = new ForegroundColorSpan(BaseApp.getRes().getColor(R.color.c_78d9ff));
        spanString.setSpan(span, 4, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //需要设置这个clickableSpan才会有效果
        mText3.setMovementMethod(LinkMovementMethod.getInstance());
        mText3.setText(spanString);
    }

    private void setProtocolOne() {
        SpannableString spanString = new SpannableString(BaseApp.getRes().getString(R.string.guide_textone));
        //前景色
        ForegroundColorSpan span = new ForegroundColorSpan(BaseApp.getRes().getColor(R.color.c_78d9ff));
        spanString.setSpan(span, 0, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ForegroundColorSpan span2 = new ForegroundColorSpan(BaseApp.getRes().getColor(R.color.c_fa6a13));
        spanString.setSpan(span2, 6, 10, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //需要设置这个clickableSpan才会有效果
        mText1.setMovementMethod(LinkMovementMethod.getInstance());
        mText1.setText(spanString);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

package com.example.admin.everywheretrips.ui.main.adapter.main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class TripsParticPageAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> titles;

    public TripsParticPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fm);
        mFragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}

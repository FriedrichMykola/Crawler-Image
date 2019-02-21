package com.example.business.friedrich.kuzan.crawlerimage.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arellomobile.mvp.MvpAppCompatFragment;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<MvpAppCompatFragment> mFragments;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
    }

    public void addFragment(MvpAppCompatFragment fragment) {
        mFragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}

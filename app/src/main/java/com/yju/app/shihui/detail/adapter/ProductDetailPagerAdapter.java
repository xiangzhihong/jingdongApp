package com.yju.app.shihui.detail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class ProductDetailPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments=null;
    private List<String> mTitles=null;

    public ProductDetailPagerAdapter(FragmentManager fm, List<Fragment> mFragments,List<String> mTitles) {
        super(fm);
        this.mFragments =mFragments;
        this.mTitles=mTitles;
    }

    public ProductDetailPagerAdapter(FragmentManager fm, Fragment... fragments) {
        super(fm);
        this.mFragments = Arrays.asList(fragments);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}

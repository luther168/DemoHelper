package com.cn.luo.demo.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:        2017/11/14 14:59
 * NOTE:
 */
public class QuickFragmentPagerAdapter <T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mFragmentList;
    private String[] mTitles;

    public QuickFragmentPagerAdapter(FragmentManager fm, List<T> mFragmentList, String[] mTitles) {
        super(fm);
        this.mFragmentList = mFragmentList;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentList == null || position < 0 || position >= mFragmentList.size()) {
            return new Fragment();
        }
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? super.getPageTitle(position) : mTitles[position];
    }
}

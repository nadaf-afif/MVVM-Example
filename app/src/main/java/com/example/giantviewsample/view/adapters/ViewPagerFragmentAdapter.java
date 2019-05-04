package com.example.giantviewsample.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentsList;
    private String[] mPageName;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> fragmentsList, String[] pageName) {
        super(fm);
        mFragmentsList = fragmentsList;
        mPageName = pageName;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        return mPageName[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentsList.size();
    }
}

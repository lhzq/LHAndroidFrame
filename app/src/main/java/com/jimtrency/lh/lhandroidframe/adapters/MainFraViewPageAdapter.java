package com.jimtrency.lh.lhandroidframe.adapters;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jimtrency.lh.androidframe.fragments.Fragment;

import java.util.List;

/**
 * Created by Administrator on 2017\12\12 0012.
 */

public class MainFraViewPageAdapter extends FragmentPagerAdapter {

    private List<String> newTitleList;
    private List<Fragment> fragments;

    public MainFraViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainFraViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.newTitleList = newTitleList;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {

        if (null != fragments && fragments.size() > 0) {
            return fragments.size();
        }
        return 0;
    }

}

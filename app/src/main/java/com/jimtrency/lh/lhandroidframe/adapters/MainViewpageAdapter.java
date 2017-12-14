package com.jimtrency.lh.lhandroidframe.adapters;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jimtrency.lh.androidframe.fragments.Fragment;

import java.util.List;

/**
 * Created by Administrator on 2017\12\11 0011.
 */

public class MainViewpageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public MainViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainViewpageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (null != fragmentList && fragmentList.size() > 0) {
            return fragmentList.size();
        }
        return 0;
    }

}

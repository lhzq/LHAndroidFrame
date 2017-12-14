package com.jimtrency.lh.lhandroidframe.fragments;

import android.view.View;

import com.jimtrency.lh.androidframe.fragments.Fragment;
import com.jimtrency.lh.androidframe.presenter.BasePresenter;
import com.jimtrency.lh.lhandroidframe.R;

/**
 * Created by Administrator on 2017\12\11 0011.
 */

public class ThreeFragment extends Fragment{
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_main_three;
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}

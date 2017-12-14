package com.jimtrency.lh.lhandroidframe.fragments;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.jimtrency.lh.androidframe.fragments.Fragment;
import com.jimtrency.lh.androidframe.presenter.BasePresenter;
import com.jimtrency.lh.lhandroidframe.R;

import java.util.List;

/**
 * Created by Administrator on 2017\12\12 0012.
 */

public class NewsFragment extends Fragment{

    private TextView textView;
    private String title;

    public NewsFragment(){

    }

    @SuppressLint("ValidFragment")
    public NewsFragment(String title){
       this.title=title;
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initWidget() {
       textView= (TextView) F(R.id.textview);
    }

    @Override
    protected void initData() {
        super.initData();
        textView.setText(title);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}

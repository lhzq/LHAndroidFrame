package com.jimtrency.lh.lhandroidframe.fragments;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jimtrency.lh.androidframe.fragments.Fragment;
import com.jimtrency.lh.androidframe.presenter.BasePresenter;
import com.jimtrency.lh.lhandroidframe.MainActivity;
import com.jimtrency.lh.lhandroidframe.R;


/**
 * Created by Administrator on 2017\12\11 0011.
 */

public class SecondFragment extends Fragment implements View.OnClickListener{

    private DrawerLayout drawerLayout;
    private RelativeLayout rlChouTi;
    private TextView tv1,tv2,tv3,tv4;
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_main_second;
    }

    @Override
    protected void initWidget() {
        drawerLayout = (DrawerLayout) F(R.id.fragment_na);
        rlChouTi= (RelativeLayout) F(R.id.ll_chouti);
        rlChouTi.setOnClickListener(this);
        tv1= (TextView) F(R.id.textview1);
        tv1.setOnClickListener(this);
        tv2= (TextView) F(R.id.textview2);
        tv2.setOnClickListener(this);
        tv3= (TextView) F(R.id.textview3);
        tv3.setOnClickListener(this);
        tv4= (TextView) F(R.id.textview4);
        tv4.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();

    }



    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_chouti:
                drawerLayout.closeDrawers();
                break;
            case R.id.textview1:
                Toast.makeText(getActivity(),"textview1",Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                break;
            case R.id.textview2:
                Toast.makeText(getActivity(),"textview2",Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                break;
            case R.id.textview3:
                Toast.makeText(getActivity(),"textview3",Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                break;
            case R.id.textview4:
                Toast.makeText(getActivity(),"textview4",Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                break;

        }
    }
}

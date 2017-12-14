package com.jimtrency.lh.lhandroidframe;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.jimtrency.lh.androidframe.activitys.MVPBaseActivity;
import com.jimtrency.lh.androidframe.fragments.Fragment;
import com.jimtrency.lh.androidframe.presenter.BasePresenter;
import com.jimtrency.lh.lhandroidframe.adapters.MainViewpageAdapter;
import com.jimtrency.lh.lhandroidframe.fragments.FourFragment;
import com.jimtrency.lh.lhandroidframe.fragments.MainFragment;
import com.jimtrency.lh.lhandroidframe.fragments.SecondFragment;
import com.jimtrency.lh.lhandroidframe.fragments.ThreeFragment;
import com.jimtrency.lh.lhandroidframe.wdiget.MainBottom;
import com.jimtrency.lh.lhandroidframe.wdiget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends MVPBaseActivity implements MainBottom.BottomClickListener {

    private MainBottom mainBottom;
    private NoScrollViewPager viewPager;
    private List<Fragment> fragmentList;
    private Fragment mainFragment,secondFragment,threeFragment,fourFragment;
    private MainViewpageAdapter mainViewpageAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        mainBottom = (MainBottom) F(R.id.main_bottom);
        viewPager= (NoScrollViewPager) F(R.id.main_viwepage);
    }

    @Override
    protected void initData() {
        super.initData();
        mainBottom.setBottomClickListener(this)
                .setFocusTextColor(ContextCompat.getColor(mContext, R.color.color_666), ContextCompat.getColor(mContext, R.color.color_0ec8dd))
                .setSingleView(R.drawable.botton_one_after, R.drawable.bottom_one_before, "首页", 0)
                .setSingleView(R.drawable.bottom_two_after, R.drawable.bottom_two_before, "第二页", 1)
                .setSingleView(R.drawable.bottom_three_after, R.drawable.bottom_three_before, "我的项目", 2)
                .setSingleView(R.drawable.bottom_four_after, R.drawable.bottom_four_before, "天", 3);

        //初始化 fragmentList
        fragmentList=new ArrayList<>();
        mainFragment=new MainFragment();
        secondFragment=new SecondFragment();
        threeFragment=new ThreeFragment();
        fourFragment=new FourFragment();

        fragmentList.add(mainFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(threeFragment);
        fragmentList.add(fourFragment);

        mainViewpageAdapter=new MainViewpageAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(mainViewpageAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setNoScroll(true);//禁止滑动
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mainBottom.setCurrentItem(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void click(View v) {
        viewPager.setCurrentItem((int) v.getTag());
    }
}

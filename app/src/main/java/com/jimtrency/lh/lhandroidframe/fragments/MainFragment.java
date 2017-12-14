package com.jimtrency.lh.lhandroidframe.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jimtrency.lh.androidframe.fragments.Fragment;
import com.jimtrency.lh.androidframe.utils.AppUtil;
import com.jimtrency.lh.lhandroidframe.R;
import com.jimtrency.lh.lhandroidframe.adapters.MainFraViewPageAdapter;
import com.jimtrency.lh.lhandroidframe.presenter.MainFraPresenter;
import com.jimtrency.lh.lhandroidframe.view.IMainFragmentView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\12\11 0011.
 */

public class MainFragment extends Fragment<IMainFragmentView, MainFraPresenter>
        implements IMainFragmentView {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainFraViewPageAdapter fraViewPageAdapter;
    private List<Fragment> fragments;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mian_first;
    }

    @Override
    protected void initWidget() {
        tabLayout = F(R.id.main_tab);
        viewPager = F(R.id.main_fragment_viewpage);
    }

    @Override
    protected void initData() {
        super.initData();
        fragments = new ArrayList<>();
        //获取数据
        mPresenter.fetchTwoMenuData();

        //修改下划线的长度
        reflex(tabLayout);


    }

    @Override
    protected MainFraPresenter createPresenter() {
        return new MainFraPresenter(this);
    }

    @Override
    public void secondLevelMenu(List<String> newTitleList) {

        int length=newTitleList.size();

        for (int i=0;i<length;i++){
            Fragment fragment=new NewsFragment(newTitleList.get(i));
            fragments.add(fragment);

            //tabLayout.addTab(tabLayout.newTab().setText(newTitleList.get(i)).setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setText(newTitleList.get(i)));
        }


        fraViewPageAdapter = new MainFraViewPageAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(fraViewPageAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);

        tabLayout.setupWithViewPager(viewPager);

        //解决tabLayout的bug
        for (int i=0;i<length;i++){
             //tabLayout可以设置图片
            //tabLayout.getTabAt(i).setText(newTitleList.get(i)).setIcon(R.mipmap.ic_launcher));

            //纯粹的使用文字
            tabLayout.getTabAt(i).setText(newTitleList.get(i));
        }

        if (length<=5){
            //不可滑动
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        }else {
            //可以滑动
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    //设置，tabLayout的下划线的大小
    public void reflex(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                    int dp10 = AppUtil.dp2px(tabLayout.getContext(), 10);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

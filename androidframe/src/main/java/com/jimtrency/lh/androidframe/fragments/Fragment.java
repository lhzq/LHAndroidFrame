package com.jimtrency.lh.androidframe.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jimtrency.lh.androidframe.presenter.BasePresenter;

/**
 * @author jimTrency
 *
 */

public abstract class Fragment<T,p extends BasePresenter<T>> extends android.support.v4.app.Fragment {
    protected View mRoot;
    protected p mPresenter;

    // 标示是否第一次初始化数据
    protected boolean mIsFirstInitData = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            int layId = getContentLayoutId();
            // 初始化当前的跟布局，但是不在创建时就添加到container里边
            mRoot = inflater.inflate(layId, container, false);
            initWidget();
            initPresenter();
        } else {
            if (mRoot.getParent() != null) {
                // 把当前Root从其父控件中移除
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }

        return mRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mIsFirstInitData) {
            // 触发一次以后就不会触发
            mIsFirstInitData = false;
            // 触发
            onFirstInit();
        }

        // 当View创建完成后初始化数据
        initData();
    }


    /**
     * 得到当前界面的资源文件Id
     *
     * @return 资源文件Id
     */
    @LayoutRes
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initWidget();

    /**
     * 初始化数据
     */
    protected  void initData() {};
    /**
     * 当首次初始化数据的时候会调用的方法
     */
    protected void onFirstInit() {

    }

    /**
     * findviewbyId的简写
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T F(int id){
        T result= (T) mRoot.findViewById(id);
        return result;
    }

    private void initPresenter(){
        //创建Presenter
        mPresenter=createPresenter();

        //内存写了，关联View
        if (mPresenter!=null){
            mPresenter.attachView((T)this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //删除关联
        if (null!=mPresenter){
            mPresenter.detachView();
        }
    }

    protected abstract p createPresenter();

}

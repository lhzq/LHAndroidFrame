package com.jimtrency.lh.androidframe.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.jimtrency.lh.androidframe.presenter.BasePresenter;


/**
 * Created by Administrator on 2017/10/22.
 */

public abstract class MVPBaseActivity<T,p extends BasePresenter<T>> extends FragmentActivity {

    protected p mPresenter;
    public Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        // 在界面未初始化之前调用的初始化窗口
        initWidows();

        // 得到界面Id并设置到Activity界面中
        int layId = getContentLayoutId();
        setContentView(layId);
        initBefore();
        initWidget();
        initPresenter();
        initData();
    }

    /**
     * 初始化窗口
     */
    protected void initWidows() {

    }

    /**
     * 得到当前界面的资源文件Id
     *
     * @return 资源文件Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件调用之前
     */
    protected void initBefore() {

    }

    /**
     * 初始化控件
     */
    protected abstract void initWidget();

    /**
     * 初始化数据
     */
    protected  void initData(){

    };

    private void initPresenter(){
        //创建Presenter
        mPresenter=createPresenter();

        //内存写了，关联View
        if (mPresenter!=null){
            mPresenter.attachView((T)this);
        }
    }
    /**
     * findviewbyId的简写
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T F(int id){
        T result= (T) findViewById(id);
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //删除关联
        if (null!=mPresenter){
            mPresenter.detachView();
        }
    }

    protected abstract p createPresenter();

}

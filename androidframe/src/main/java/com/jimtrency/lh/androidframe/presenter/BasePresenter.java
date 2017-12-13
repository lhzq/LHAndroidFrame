package com.jimtrency.lh.androidframe.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/10/22.
 */

public abstract class BasePresenter<T> {
    /**
     * 当前内存不足可释放内存
     */
    protected WeakReference<T> mViewRef;

    public void attachView(T view) {
        mViewRef=new WeakReference<T>(view);
    }

    public void detachView(){
        if (null!=mViewRef){
            mViewRef.clear();
            mViewRef=null;
        }
    }

    protected T getView() {
        return mViewRef.get();
    }


}

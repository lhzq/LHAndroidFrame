package com.jimtrency.lh.lhandroidframe.presenter;

import android.content.Context;

import com.jimtrency.lh.androidframe.presenter.BasePresenter;
import com.jimtrency.lh.lhandroidframe.model.SplashModelImpl;
import com.jimtrency.lh.lhandroidframe.view.ISplashView;

/**
 * Created by Administrator on 2017/11/17.
 */

public class SplashPresenter extends BasePresenter<ISplashView> {
    private ISplashView splashView;
    private SplashModelImpl splashModel;

    /**
     * 通过构造方法实现 girlView
     * @param splashView
     */
    public SplashPresenter(ISplashView splashView, Context mContext) {
        this.splashView = splashView;
        splashModel=new SplashModelImpl(mContext);
    }

    //获取数据
    public void fetch(){
        splashModel.loadImage(new SplashModelImpl.GrilOnLoadListener() {
            @Override
            public void onComplete(String url,String skipUrl) {
                splashView.showImage(url,skipUrl);
            }
        });
    }
}

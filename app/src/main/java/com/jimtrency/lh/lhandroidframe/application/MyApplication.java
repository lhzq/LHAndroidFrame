package com.jimtrency.lh.lhandroidframe.application;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by Administrator on 2017/11/19.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  null);
    }

}

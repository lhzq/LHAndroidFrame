package com.jimtrency.lh.androidframe.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/19.
 */

public class RetrofitUtil {

    private static volatile RetrofitUtil mInstance;//单利引用
    /**
     * 获取单例引用
     *
     * @return
     */
    public static RetrofitUtil getInstance() {
        RetrofitUtil inst = mInstance;
        if (inst == null) {
            synchronized (RetrofitUtil.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new RetrofitUtil();
                    mInstance = inst;
                }
            }
        }
        return inst;
    }

    public  <T> T getAPi(String baseUrl,final Class<T> service){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

}

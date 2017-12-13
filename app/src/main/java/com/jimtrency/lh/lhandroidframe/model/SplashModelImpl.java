package com.jimtrency.lh.lhandroidframe.model;

import android.content.Context;

import com.google.gson.Gson;
import com.jimtrency.lh.androidframe.utils.AppUtil;
import com.jimtrency.lh.androidframe.utils.RetrofitUtil;
import com.jimtrency.lh.lhandroidframe.bean.ImageBanderInfo;
import com.jimtrency.lh.lhandroidframe.constant.Api;
import com.jimtrency.lh.lhandroidframe.constant.Net;
import com.jimtrency.lh.lhandroidframe.datagrams.DatagramRequest;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/17.
 */

public class SplashModelImpl {

    private Context mContext;
    private int screenScale;

    public SplashModelImpl(Context context) {
        this.mContext = context;
    }

    //连网动态获取所需的图片
    public void loadImage(final GrilOnLoadListener loadListener) {
        //这里进行连网操作
        DatagramRequest datagramRequest = new DatagramRequest(mContext);
        datagramRequest
                .setRequestSystem("nxt")
                .putParameter("recommendId", "LOADING")
                .setRequestMethod("XzSlideShow/get");

        Api api = RetrofitUtil.getInstance().getAPi(Net.SERVER_URL, Api.class);

        Observable<ResponseBody> observable = api.xzSlideShow(datagramRequest);
        observable.subscribeOn(Schedulers.io()).flatMap(new Function<ResponseBody, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(ResponseBody responseBody) throws Exception {
                return Observable.just(new String(responseBody.bytes()));
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String str) throws Exception {
                Gson gson = new Gson();
                ImageBanderInfo info = gson.fromJson(str, ImageBanderInfo.class);
                if (info.mobileRespHeader.respCode.equals("0") || info.mobileRespHeader.respCode.equals("2000")) {
                    adaperScreen();
                    String url = info.mobileRespBody.data.get(screenScale).imageUrl;
                    String skipUrl = info.mobileRespBody.data.get(screenScale).url;
                    loadListener.onComplete(url, skipUrl);
                }
            }
        });
    }

    public interface GrilOnLoadListener {

        void onComplete(String url, String skipUrl);
    }

    private void adaperScreen() {
        int screenHeight = AppUtil.getScreenHeight(mContext);
        if (screenHeight <= 960) {
            screenScale = 0;
        } else if (screenHeight > 960 && screenHeight <= 1136) {
            screenScale = 1;
        } else if (screenHeight > 1136 && screenHeight <= 1280) {
            screenScale = 2;
        } else if (screenHeight > 1280 && screenHeight <= 1334) {
            screenScale = 3;
        } else if (screenHeight > 1334 && screenHeight <= 1920) {
            screenScale = 4;
        } else if (screenHeight > 1920) {
            screenScale = 5;
        }
    }
}

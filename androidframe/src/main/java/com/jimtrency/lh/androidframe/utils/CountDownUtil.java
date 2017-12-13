package com.jimtrency.lh.androidframe.utils;

import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/11/24.
 */

public class CountDownUtil {

    private Disposable disposable;

    //倒计时
    public void countDown(final int secondCount, final TextView view, final CountDownClickListener clickListener, final CompleteListener completeListener) {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(secondCount)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        CountDownUtil.this.disposable=disposable;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        view.setText("第 " + (secondCount - value) + " 秒");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        //  view.setEnabled(true);
                        completeListener.complete();
                    }
                });
        //点击图片，跳到对应的网页
        RxView.clicks(view).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                clickListener.click();
            }
        });
    }

    public void countDown(final int secondCount, final TextView view, final CompleteListener completeListener) {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(secondCount)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.setEnabled(false);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        view.setText("第 " + (secondCount - value) + " 秒");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        view.setEnabled(true);
                        completeListener.complete();
                    }
                });
    }

    //取消订阅
    public void unSubscribe(){
        if (disposable!=null){
            disposable.dispose();
        }
    }


    public interface CompleteListener {
        void complete();
    }

    public interface CountDownClickListener {
        void click();
    }
}

package com.jimtrency.lh.lhandroidframe.activitys;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jakewharton.rxbinding2.view.RxView;
import com.jimtrency.lh.androidframe.activitys.MVPBaseActivity;
import com.jimtrency.lh.androidframe.utils.CountDownUtil;
import com.jimtrency.lh.lhandroidframe.MainActivity;
import com.jimtrency.lh.lhandroidframe.R;
import com.jimtrency.lh.lhandroidframe.presenter.SplashPresenter;
import com.jimtrency.lh.lhandroidframe.view.ISplashView;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/11/17.
 */

public class SplashActivity extends MVPBaseActivity<ISplashView, SplashPresenter> implements ISplashView {

    //加载动态启动页的容器
    private RelativeLayout rlLoadingContainter;
    //倒计时Textview
    private TextView tvCount;
    private ImageView ivLoading;
    private final int URL_BACK_OK = 333;
    private CountDownUtil downUtil;


    @Override
    protected void initWidows() {
        super.initWidows();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
   
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initWidget() {
        rlLoadingContainter = F(R.id.rl_loading);
        tvCount = F(R.id.tv_count);
        ivLoading = F(R.id.iv_loading);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.fetch();
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this, mContext);
    }

    //回调，加载的动态图片
    @Override
    public void showImage(String url, final String skipUrl) {
        rlLoadingContainter.setVisibility(View.VISIBLE);

        Glide.with(this).load(url).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //倒计时，时间一到跳到主界面
                downUtil = new CountDownUtil();
                downUtil.countDown(10, tvCount, new CountDownUtil.CountDownClickListener() {
                    @Override
                    public void click() {
                        gotoMainActivity();
                        downUtil.unSubscribe();
                        finish();
                    }
                }, new CountDownUtil.CompleteListener() {
                    @Override
                    public void complete() {
                        //跳转到主页面
                        gotoMainActivity();
                        finish();
                    }
                });
                return false;
            }
        }).into(ivLoading);

        //点击图片，跳到对应的网页
        RxView.clicks(ivLoading).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url", skipUrl);
                startActivityForResult(intent, URL_BACK_OK);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == URL_BACK_OK) {
            gotoMainActivity();
            finish();
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        if (downUtil != null) {
            downUtil.unSubscribe();
        }
    }
}

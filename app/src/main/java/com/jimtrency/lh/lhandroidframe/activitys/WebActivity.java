package com.jimtrency.lh.lhandroidframe.activitys;

import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.jimtrency.lh.androidframe.activitys.MVPBaseActivity;
import com.jimtrency.lh.androidframe.presenter.BasePresenter;
import com.jimtrency.lh.lhandroidframe.R;
import com.jimtrency.lh.lhandroidframe.widget.webview.CustomChromeClient;
import com.jimtrency.lh.lhandroidframe.widget.webview.X5Webview;

/**
 * Created by Administrator on 2017/11/20.
 */

public class WebActivity extends MVPBaseActivity{

    private X5Webview x5Webview;
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initWidget() {
        x5Webview= (X5Webview) F(R.id.x5webview);
        x5Webview.setWebChromeClient(new CustomChromeClient());
    }


    @Override
    protected void initData() {
        super.initData();
        String url=getIntent().getStringExtra("url");
        x5Webview.show("http://183.230.100.168:444/coursedetail.html?id=284");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
           x5Webview.back();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        x5Webview.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}

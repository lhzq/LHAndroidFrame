package com.jimtrency.lh.lhandroidframe.widget.webview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public class X5Webview extends WebView implements DownloadListener {

    private WebSettings webSettings;
    private Context mContext;
    private final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 136;
    private String phoneNumber;
    private String tempUrl, latestUrl,lastUrl;
    private List<String> urlCollection;

    public X5Webview(Context context) {
        super(context);
        init(context);
    }

    public X5Webview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public X5Webview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public X5Webview(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
        init(context);
    }

    //初始化webview
    private void init(Context context) {
        mContext = context;

        urlCollection=new ArrayList<>();
        setDownloadListener(this);
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        webSettings = getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(context.getApplicationContext().getCacheDir().getAbsolutePath());
        webSettings.setAppCacheMaxSize(1024 * 1024 * 9);// 设置缓冲大小
        webSettings.setSavePassword(false);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        setWebViewClient(mWebViewClient);

    }

    @Override
    public void onDownloadStart(String url, String s1, String s2, String s3, long l) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }

    private WebViewClient mWebViewClient = new WebViewClient() {

        // 接受证书

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, com.tencent.smtt.export.external.interfaces.SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (!TextUtils.isEmpty(url) && url.contains("tel")) {
                 phoneNumber = url.substring(url.indexOf(":") + 1, url.length());
                if (!TextUtils.isEmpty(phoneNumber)) {
                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) mContext,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    } else {
                        Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        mContext.startActivity(intentPhone);
                    }

                }
            }else {
                if (!TextUtils.isEmpty(url)) {
                    latestUrl = url;
                    if (tempUrl.equals(latestUrl)) {
                        ((Activity)mContext).finish();
                    } else  if (!TextUtils.isEmpty(lastUrl) && lastUrl.equals(latestUrl)){
                        ((Activity)mContext).finish();
                    }else {
                        loadUrl(url);
                        tempUrl = url;
                        urlCollection.add(tempUrl);
                    }
                }

            }
            return true;
        }
    };

    //设置wbview头的信息
    public void setWebAgent(String agent){
        getSettings().setUserAgentString( getSettings().getUserAgentString() + agent);
    }

    public void setMyWebViewClient(com.tencent.smtt.sdk.WebViewClient mWebViewClient){
        this.mWebViewClient=mWebViewClient;
        setWebViewClient(mWebViewClient);
    }

    public void show(String url){
        tempUrl = url;
        urlCollection.add(tempUrl);
        loadUrl(url);
    }


    @SuppressLint("MissingPermission")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                    mContext.startActivity(intentPhone);
                }
                break;
        }

    }

    public void back() {
        if (canGoBack()) {
            if (latestUrl == null) {
                ((Activity)mContext).finish();
            } else {

                try {
                    if (urlCollection != null && urlCollection.size() > 0) {
                        tempUrl = urlCollection.get(urlCollection.size() - 2);
                        lastUrl=urlCollection.get(urlCollection.size()-1);
                        urlCollection.remove(urlCollection.size() - 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                goBack();

            }
        }else {
            ((Activity)mContext).finish();
        }

    }


}

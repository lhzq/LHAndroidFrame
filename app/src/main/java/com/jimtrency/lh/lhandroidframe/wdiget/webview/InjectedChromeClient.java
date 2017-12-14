package com.jimtrency.lh.lhandroidframe.wdiget.webview;

import android.util.Log;

import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;


public class InjectedChromeClient extends WebChromeClient {
    private final String TAG = "InjectedChromeClient";
    private JsCallJava mJsCallJava;
    private boolean mIsInjectedJS;

    public InjectedChromeClient(String injectedName, Class injectedCls) {
        mJsCallJava = new JsCallJava(injectedName, injectedCls);
    }

    public InjectedChromeClient(JsCallJava jsCallJava) {
        mJsCallJava = jsCallJava;
    }

    public InjectedChromeClient() {

    }

    // 处理Alert事件

    @Override
    public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {

        return true;
    }



    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (mJsCallJava != null) {
            if (newProgress <= 25) {
                mIsInjectedJS = false;
            } else if (!mIsInjectedJS) {

                view.loadUrl(mJsCallJava.getPreloadInterfaceJS());
                mIsInjectedJS = true;
                Log.d(TAG, " inject js interface completely on progress " + newProgress);

            }
        }
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public boolean onJsPrompt(WebView webView, String s, String message, String s2, JsPromptResult jsPromptResult) {
        if (mJsCallJava != null) {
            jsPromptResult.confirm(mJsCallJava.call(webView, message));
        }
        return true;
    }
}


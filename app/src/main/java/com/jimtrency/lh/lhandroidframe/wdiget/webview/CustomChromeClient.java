package com.jimtrency.lh.lhandroidframe.wdiget.webview;


import com.tencent.smtt.sdk.WebView;

/**
 * Created by NXBJB161 on 2017/9/29.
 */

public class CustomChromeClient extends InjectedChromeClient {

    public CustomChromeClient(String injectedName, Class injectedCls) {
        super(injectedName, injectedCls);
    }

    public CustomChromeClient(JsCallJava jsCallJava) {
        super(jsCallJava);
    }

    public CustomChromeClient() {
        super();

    }

    @Override
    public boolean onJsAlert(com.tencent.smtt.sdk.WebView view, String url, String message, com.tencent.smtt.export.external.interfaces.JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public void onProgressChanged(com.tencent.smtt.sdk.WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, com.tencent.smtt.export.external.interfaces.JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

}

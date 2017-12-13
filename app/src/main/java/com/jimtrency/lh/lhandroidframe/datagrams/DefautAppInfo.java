package com.jimtrency.lh.lhandroidframe.datagrams;

import android.content.Context;

import com.jimtrency.lh.androidframe.utils.AppUtil;

/**
 * Created by Administrator on 2017/11/19.
 */

public class DefautAppInfo extends AppBaseInfo{

    public String appid;
    public String appCode;
    public String verName;
    public String chnlCode;
    public String platform;
    public String verCode;
    public String userKey;

    public DefautAppInfo(Context context){
        appid = context.getPackageName();
        appCode = "itown";//AppUtil.getMetaData(context, "APPCODE");
        verCode = AppUtil.getVersionCode(context) + "";
        platform = "android";
        verName = AppUtil.getVersionName(context);
        userKey = AppUtil.getIMEI(context);
        chnlCode = AppUtil.getMetaData(context, "UMENG_CHANNEL").equals("") ? "cm360" : AppUtil.getMetaData(context, "UMENG_CHANNEL");

    }
}

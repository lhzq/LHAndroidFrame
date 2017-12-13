/*
 * Copyright (C) 2012 重庆新媒农信科技有限公司
 * 版权所有
 *
 * 功能描述：获取系统信息
 *
 *
 * 创建标识：duxl 20130730
 */
package com.jimtrency.lh.androidframe.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.IOException;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class AppUtil {

    /**
     * 获取TelephonyManager
     *
     * @param context
     * @return
     */
    public static TelephonyManager getTelephonyManager(Context context) {
        TelephonyManager telMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telMgr;
    }

    /**
     * 获取"渠道号"等在manifest内定义的meta值
     *
     * @param context
     * @param key
     * @return
     */
    public static String getMetaData(Context context, String key) {
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            Object value = ai.metaData.get(key);
            if (value != null) {
                return value.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

        //ChannelUtil.getChannel(context,key);
    }

    /**
     * 获取网络连接类型
     *
     * @param context
     * @return
     */
    public static String getNetWorkType(Context context) {
        TelephonyManager telMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String nt = "";
        if (telMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_EDGE) {
            nt = "EDGE";
        } else if (telMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_GPRS) {
            nt = "GPRS";
        } else if (telMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS) {
            nt = "UMTS";
        } else if (telMgr.getNetworkType() == 4) {
            nt = "HSDPA";
        } else {
        }
        return nt;
    }

    /**
     * 获取IMSI
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        TelephonyManager telMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = "";
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {


        }
        imsi = telMgr.getSubscriberId();

        return imsi;
    }

    /**
     * 获取IMEI
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        String tephoneDeviceID = "";
        if (ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    100);
        } else {
            TelephonyManager telMgr = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            /**
             * 由于获取imei后需要动态权限，并且是异步且会因为用户的不同选择而不同，现在修改为获取anroidid得形式
             */
            tephoneDeviceID = telMgr.getDeviceId();
        }


        return tephoneDeviceID;
    }

    /**
     * 获取运营商所在国家
     *
     * @param context
     * @return
     */
    public static String getCountry(Context context) {
        TelephonyManager telMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String country = "";
        country = telMgr.getSimCountryIso();
        return country;
    }

    /**
     * 获得VersionCode，返回值为版本号，例如：5
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "get versionCode Exception(RuntimeException)");
        }
    }

    /**
     * 获得versionName，返回值为版本名称，例如：1.3.1
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "get versionCode Exception(RuntimeException)");
        }
    }






    /**
     * 获取设备信息
     *
     * @return
     */
    public static String getDeviceInfo() {
        String handSetInfo = "手机型号:" + android.os.Build.MODEL + ",_:"
                + android.os.Build.VERSION.RELEASE;
        return handSetInfo;
    }

    /**
     * 获取Classes.dex中的SHA1值
     *
     * @param ctx
     * @return SHA1
     */
    @SuppressWarnings({"resource", "finally"})
    public static String getClassesSHA1(Context ctx) {
        String sha1 = "";
        try {
            ApplicationInfo ai = ctx.getApplicationInfo();
            String source = ai.sourceDir;
            JarFile jar;
            jar = new JarFile(source);
            Manifest mf = jar.getManifest();
            Map<String, Attributes> map = mf.getEntries();
            Attributes a = map.get("classes.dex");
            sha1 = (String) a.getValue("SHA1-Digest");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return sha1;
        }

    }

    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

}

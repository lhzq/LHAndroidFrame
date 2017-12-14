package com.jimtrency.lh.lhandroidframe.constant;

/**
 * Created by Administrator on 2017/11/19.
 */

public class Net {
    public static boolean isTest = false;// 是否为测试环境

    //服务器地址 http://183.230.101.153:18080/
    public static String SERVER_URL =
            isTest ? "http://113.204.229.74:6888/" : "https://iosapi.12582.cn/";
}

package com.jimtrency.lh.lhandroidframe.datagrams;

import android.content.Context;

/**
 * Created by Administrator on 2017/11/19.
 */

public class RequestHeader<T extends AppBaseInfo, P extends AuthBaseInfo> {
    public T appInfo;
    public P authInfo;

    public RequestHeader(Context context) {

        if (appInfo == null && authInfo == null) {
            appInfo = (T) new DefautAppInfo(context);
            authInfo = (P) new DefautAuthorInfo();
        }
    }


}

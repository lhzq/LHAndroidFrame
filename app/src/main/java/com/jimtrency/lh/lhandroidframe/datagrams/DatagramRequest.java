package com.jimtrency.lh.lhandroidframe.datagrams;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/11/19.
 */

public class DatagramRequest<T extends AppBaseInfo,P extends AuthBaseInfo> {

    public RequestHeader<T,P> mobileReqHeader;
    public RequestBody mobileReqBody;

    public DatagramRequest(Context context){
        mobileReqHeader=new RequestHeader<T,P>(context);
        mobileReqBody=new RequestBody();
    }

    public DatagramRequest setRequestMethod(String method){
        mobileReqBody.setMethod(method);
        return this;
    }

    public DatagramRequest setRequestSystem(String system){
        mobileReqBody.setSystem(system);
        return this;
    }

    public DatagramRequest putParameter(String key, Object value){
        mobileReqBody.putParameter(key,value);
        return this;
    }

    /**
     *
     *  将当前对象转化为 JSON字符串
     */
    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this, this.getClass());
        //灰度接口比较奇葩  parameter是List  不是Map  所以只能这样玩
      //  mobileReqBody.clearParameter();
        return json;
    }
}

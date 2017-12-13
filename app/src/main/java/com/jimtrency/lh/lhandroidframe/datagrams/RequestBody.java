package com.jimtrency.lh.lhandroidframe.datagrams;

import com.jimtrency.lh.lhandroidframe.constant.GlobalConstant;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/19.
 */

public class RequestBody{

    public HashMap<String, Object> parameter;
    public ServReqInfo servReqInfo;

    public RequestBody(){
        servReqInfo = new ServReqInfo();
    }
    public class ServReqInfo {
        public String system = GlobalConstant.DATAGRAM_SYSTEM;
        public String method;
    }

    public void setMethod(String method){
        servReqInfo.method = method;
    }

    public void setSystem(String system){
        servReqInfo.system = system;
    }

    public void putParameter(String key, Object value){
        if(null == parameter){
            parameter = new HashMap<String, Object>();
        }
        parameter.put(key, value);
    }

    public void clearParameter(){
        if (null!=parameter){
            parameter.clear();
        }
    }

}

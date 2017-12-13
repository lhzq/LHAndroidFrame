package com.jimtrency.lh.lhandroidframe.constant;

import com.jimtrency.lh.lhandroidframe.datagrams.DatagramRequest;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/11/19.
 */

public interface Api {

    /**
     * 把下面的request 参数自动转换为Json
     * @param request
     * @return
     */
    @POST("esb/")
    Observable<ResponseBody> xzSlideShow(@Body DatagramRequest request);
}

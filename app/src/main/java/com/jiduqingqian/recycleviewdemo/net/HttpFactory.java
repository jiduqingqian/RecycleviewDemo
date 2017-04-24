package com.jiduqingqian.recycleviewdemo.net;

/**
 * Created by qianhao on 2017/4/20.
 */

public class HttpFactory {
    public static IHttpRequest getHttpRequest() {
        return new OkHttpRequestImpl();
    }
}

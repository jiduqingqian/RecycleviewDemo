package com.jiduqingqian.recycleviewdemo.net;

/**
 * Created by qianhao on 2017/4/20.
 */

public interface IHttpRequest {
    void get(String url, NetResponseHandler responseHandler);

    void post(String url, NetResponseHandler responseHandler);

    void cancel(String tag);
}

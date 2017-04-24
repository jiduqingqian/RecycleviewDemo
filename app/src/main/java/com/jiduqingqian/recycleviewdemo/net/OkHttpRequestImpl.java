package com.jiduqingqian.recycleviewdemo.net;

import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by qianhao on 2017/4/20.
 */

public class OkHttpRequestImpl implements IHttpRequest {
    @Override
    public void get(String url, NetResponseHandler responseHandler) {

        OkHttpUtils.get().url(url).build().execute(responseHandler);

    }

    @Override
    public void post(String url, NetResponseHandler responseHandler) {

    }

    @Override
    public void cancel(String tag) {

    }
}

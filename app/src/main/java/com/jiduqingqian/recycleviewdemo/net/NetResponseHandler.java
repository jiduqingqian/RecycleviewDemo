package com.jiduqingqian.recycleviewdemo.net;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by qianhao on 2017/4/20.
 */

public abstract class NetResponseHandler<T> extends Callback<T> {
    public abstract void success(T data);

    public abstract void failure();

    public void complete() {
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) string;
        }
        T bean = new Gson().fromJson(string, entityClass);

        return bean;
    }

    @Override
    public void onResponse(T response, int id) {
        complete();
        success(response);
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        complete();
        failure();
    }
}

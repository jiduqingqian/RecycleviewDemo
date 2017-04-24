package com.jiduqingqian.recycleviewdemo.adapter.holder;

import android.view.View;

import com.jiduqingqian.recycleviewdemo.module.Meizi;

/**
 * Created by qianhao on 2017/4/21.
 */

public interface TypeFactory {
    int type(Meizi m);

    int type(String string);

    BaseViewHolder creatViewHolder(int viewType, View view);
}

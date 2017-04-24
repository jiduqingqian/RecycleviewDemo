package com.jiduqingqian.recycleviewdemo.adapter.holder;

import android.view.View;

import com.jiduqingqian.recycleviewdemo.R;
import com.jiduqingqian.recycleviewdemo.module.Meizi;

/**
 * Created by qianhao on 2017/4/21.
 */

public class TypeFactoryForGrid implements TypeFactory {
    private final int typeOne = R.layout.grid_meizi_item;
    private final int typeTwo = R.layout.page_item;

    @Override
    public int type(Meizi m) {
        return typeOne;
    }

    @Override
    public int type(String string) {
        return typeTwo;
    }

    @Override
    public BaseViewHolder creatViewHolder(int viewType, View view) {
        switch (viewType) {
            case typeOne:
                return new TypeOneViewHolder(view);
            case typeTwo:
                return new TypeTwoViewHolder(view);
        }

        return null;
    }
}

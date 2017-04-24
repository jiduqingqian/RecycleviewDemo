package com.jiduqingqian.recycleviewdemo.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by qianhao on 2017/4/21.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    SparseArray<View> views;
    View              itemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
        this.itemView = itemView;
    }

    public abstract void bindView(T t);

    public View getView(int resID) {
        View view = views.get(resID);
        if (view == null) {
            view = itemView.findViewById(resID);
            views.put(resID, view);
        }

        return view;
    }
}

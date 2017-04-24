package com.jiduqingqian.recycleviewdemo.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.jiduqingqian.recycleviewdemo.R;

public class TypeTwoViewHolder extends BaseViewHolder<String> {
    public TextView tv;

    public TypeTwoViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(String s) {
        tv = (TextView) getView(R.id.tv);
        tv.setText(s);
    }
}
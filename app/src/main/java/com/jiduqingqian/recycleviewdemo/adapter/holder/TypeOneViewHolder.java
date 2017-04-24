package com.jiduqingqian.recycleviewdemo.adapter.holder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.jiduqingqian.recycleviewdemo.BigImageActivity;
import com.jiduqingqian.recycleviewdemo.R;
import com.jiduqingqian.recycleviewdemo.module.Meizi;
import com.jiduqingqian.recycleviewdemo.utils.image.ImageLoaderUtil;


public class TypeOneViewHolder extends BaseViewHolder<Meizi> {
    private View v;

    public TypeOneViewHolder(View itemView) {
        super(itemView);
        v = itemView;
    }

    @Override
    public void bindView(final Meizi meizi) {
        ImageView imageView = (ImageView) getView(R.id.image);
        ImageLoaderUtil.loadImage(meizi.getUrl(), imageView, 10);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), BigImageActivity.class);
                intent.putExtra("url", meizi.getUrl());
                v.getContext().startActivity(intent);
            }
        });
    }
}
package com.jiduqingqian.recycleviewdemo.utils.image;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiduqingqian.recycleviewdemo.BaseApplication;

/**
 * Created by qianhao on 2017/4/20.
 */

public class ImageLoaderUtil {

    //默认按控件大小加载图片
    public static void loadImage(String url, ImageView imageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .into(imageView);
    }


    public static void loadCircleImage(String url, ImageView imageView) {

        Glide.with(BaseApplication.getContext())
                .load(url).transform(new GlideCircleTransform(BaseApplication.getContext()))
                .into(imageView);

    }

    //默认按控件大小加载图片
    public static void loadImage(String url, ImageView imageView, int round) {
        Glide.with(BaseApplication.getContext())
                .load(url).transform(new GlideRoundTransform(BaseApplication.getContext(), round))
                .into(imageView);
    }
}

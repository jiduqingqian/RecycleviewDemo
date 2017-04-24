package com.jiduqingqian.recycleviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.jiduqingqian.recycleviewdemo.utils.image.ImageLoaderUtil;
import com.jiduqingqian.recycleviewdemo.view.OnSildingFinishListener;
import com.jiduqingqian.recycleviewdemo.view.SlidingFinishRelativeLayout;

public class BigImageActivity extends Activity {
    private String                      imageUrl;
    private ImageView                   imageView;
    private SlidingFinishRelativeLayout activity_big_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        activity_big_image = (SlidingFinishRelativeLayout) findViewById(R.id.activity_big_image);
        imageView = (ImageView) findViewById(R.id.image);
        imageUrl = getIntent().getStringExtra("url");
        ImageLoaderUtil.loadImage(imageUrl, imageView);

        activity_big_image.setTouchView(imageView);
        activity_big_image.setOnSildingFinishListener(new OnSildingFinishListener() {
            @Override
            public void onSildingFinish() {
                finish();
            }
        });
    }
}

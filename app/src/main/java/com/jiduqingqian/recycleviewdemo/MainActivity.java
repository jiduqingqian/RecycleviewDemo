package com.jiduqingqian.recycleviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.jiduqingqian.recycleviewdemo.adapter.GridAdapter;
import com.jiduqingqian.recycleviewdemo.module.ResultModule;
import com.jiduqingqian.recycleviewdemo.net.HttpFactory;
import com.jiduqingqian.recycleviewdemo.net.NetResponseHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView recyclerview;
    StaggeredGridLayoutManager mLayoutManager;
    private GridAdapter gridAdapter;
    private List<Object> meizis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(mLayoutManager);

        HttpFactory.getHttpRequest().get("http://gank.io/api/data/福利/10/1", new NetResponseHandler<ResultModule>() {


            @Override
            public void success(final ResultModule data) {
                meizis.addAll(data.getResults());
                meizis.add(1, "第一个");
                meizis.add(4, "第四个");
                meizis.add(7, "第七个");
                meizis.add(9, "第九个");
                recyclerview.setAdapter(gridAdapter = new GridAdapter(meizis));
            }

            @Override
            public void failure() {

            }
        });


    }
}

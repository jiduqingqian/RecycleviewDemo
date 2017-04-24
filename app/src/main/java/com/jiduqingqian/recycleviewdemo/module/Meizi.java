package com.jiduqingqian.recycleviewdemo.module;

import com.jiduqingqian.recycleviewdemo.adapter.holder.TypeFactory;

public class Meizi implements Visitable {
    private String url;
    private String desc;

    public Meizi() {
    }

    public Meizi(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int type(TypeFactory factory) {
        return factory.type(this);
    }
}
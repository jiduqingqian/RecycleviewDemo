package com.jiduqingqian.recycleviewdemo.module;

import java.util.List;

/**
 * Created by qianhao on 2017/4/20.
 */

public class ResultModule {
    private boolean     error;
    private List<Meizi> results;

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Meizi> getResults() {
        return results;
    }

    public void setResults(List<Meizi> results) {
        this.results = results;
    }
}

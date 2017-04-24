package com.jiduqingqian.recycleviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiduqingqian.recycleviewdemo.R;
import com.jiduqingqian.recycleviewdemo.adapter.holder.BaseViewHolder;
import com.jiduqingqian.recycleviewdemo.adapter.holder.TypeFactoryForGrid;
import com.jiduqingqian.recycleviewdemo.module.Meizi;

import java.util.List;


public class GridAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Object>       meizis;
    private TypeFactoryForGrid typeFactory;

    public GridAdapter(List<Object> meizis) {
        this.meizis = meizis;
        typeFactory = new TypeFactoryForGrid();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return typeFactory.creatViewHolder(viewType, view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.bindView(meizis.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (meizis.get(position) instanceof Meizi) {
            return R.layout.grid_meizi_item;
        }
        return R.layout.page_item;
    }

    @Override
    public int getItemCount() {
        return meizis.size();
    }
}


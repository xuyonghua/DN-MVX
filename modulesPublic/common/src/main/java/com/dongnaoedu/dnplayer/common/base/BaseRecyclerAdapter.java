package com.dongnaoedu.dnplayer.common.base;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public abstract class BaseRecyclerAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K>
        implements LoadMoreModule {

    public BaseRecyclerAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public BaseRecyclerAdapter(int layoutResId) {
        super(layoutResId);
    }
}
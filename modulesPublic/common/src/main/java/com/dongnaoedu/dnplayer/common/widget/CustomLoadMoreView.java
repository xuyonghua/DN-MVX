package com.dongnaoedu.dnplayer.common.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dongnaoedu.dnplayer.common.R;

/**
 * BaseQuickAdapter LoadMoreView
 */
public class CustomLoadMoreView extends BaseLoadMoreView {

    @Override
    public View getRootView(ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_load_more, viewGroup, false);
    }

    @Override
    public View getLoadComplete(BaseViewHolder holder) {
        return holder.findView(R.id.load_more_load_complete_view);
    }

    @Override
    public View getLoadEndView(BaseViewHolder holder) {
        return holder.findView(R.id.load_more_load_end_view);
    }

    @Override
    public View getLoadFailView(BaseViewHolder holder) {
        return holder.findView(R.id.load_more_load_fail_view);
    }

    @Override
    public View getLoadingView(BaseViewHolder holder) {
        return holder.findView(R.id.load_more_loading_view);
    }

}
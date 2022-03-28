package com.dongnaoedu.dnplayer.main;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dongnaoedu.dnplayer.common.base.BaseRecyclerAdapter;
import com.dongnaoedu.dnplayer.common.data.entity.MusicInfo;
import com.dongnaoedu.dnplayer.common.glide.GlideApp;
import com.dongnaoedu.dnplayer.common.utils.DisplayUtil;
import com.dongnaoedu.dnplayer.main.databinding.ItemHomeSheetBinding;

public class HomeSheetAdapter extends BaseRecyclerAdapter<MusicInfo, HomeSheetAdapter.ViewHolder> {

    public HomeSheetAdapter() {
        super(R.layout.item_home_sheet);
    }

    @Override
    protected void convert(ViewHolder helper, MusicInfo item) {
        helper.binding.tvTitle.setText(item.getMusicName() + " - " + item.getMusicDesc());
//        GlideApp.with(helper.itemView)
//                .load(item.getMusicPicUrl())
//                .applyAlbumPic(144 * 2, 4)
//                .into(helper.binding.ivPic);
    }

    @Override
    public BaseLoadMoreModule addLoadMoreModule(BaseQuickAdapter<?, ?> baseQuickAdapter) {
        return null;
    }

    final static class ViewHolder extends BaseViewHolder {

        ItemHomeSheetBinding binding;

        public ViewHolder(View view) {
            super(view);
            try {
                binding = ItemHomeSheetBinding.bind(view);
                int itemWith = (DisplayUtil.getScreenWidth() - DisplayUtil.dp2px(48)) / 3;
                ViewGroup.LayoutParams layoutParams = binding.layoutItem.getLayoutParams();
                layoutParams.width = itemWith;
                layoutParams.height = itemWith;
                binding.layoutItem.setLayoutParams(layoutParams);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

}
package com.dongnaoedu.dnplayer.main.adapter;

import android.widget.ImageView;

import com.dongnaoedu.dnplayer.common.glide.GlideApp;

import androidx.databinding.BindingAdapter;

/**
 * DataBinding提供数据绑定的Adapter
 */
public class ImageViewAdapter {

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, String url) {
        GlideApp.with(imageView)
                .load(url)
                .applyAvatar(144 * 2)
                .into(imageView);
    }

}

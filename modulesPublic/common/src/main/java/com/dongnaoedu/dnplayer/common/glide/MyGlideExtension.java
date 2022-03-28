package com.dongnaoedu.dnplayer.common.glide;


import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.dongnaoedu.dnplayer.common.R;
import com.dongnaoedu.dnplayer.common.utils.DisplayUtil;

import androidx.annotation.NonNull;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 自定义可拓展的API，达到复用目的
 */
@GlideExtension
public final class MyGlideExtension {

    private MyGlideExtension() {
    } // utility class

    @NonNull
    @GlideOption
    public static BaseRequestOptions<?> applyAvatar
            (BaseRequestOptions<?> options, int size) {
        return options
                .placeholder(R.mipmap.img_avatar_default)
                .error(R.mipmap.img_avatar_default)
                .override(size)
                .circleCrop();
    }

    @NonNull
    @GlideOption
    public static BaseRequestOptions<?> applyAlbumPic
            (BaseRequestOptions<?> options, int size, int radius) {
        return options
                .override(size)
                .transform(new MultiTransformation<>(new CenterCrop(),
                        new RoundedCornersTransformation(DisplayUtil.dp2px(radius), 0)));
    }


}

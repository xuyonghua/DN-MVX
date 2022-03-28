package com.dongnaoedu.dnplayer.libbase.init;

import android.content.Context;
import android.text.TextUtils;

import com.dongnaoedu.dnplayer.libbase.cache.AppCache;
import com.dongnaoedu.dnplayer.libbase.utils.Logger;
import com.dongnaoedu.dnplayer.libbase.utils.PropertiesUtil;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

public class LibBaseInitializer implements Initializer<Object> {

    @NonNull
    @Override
    public Object create(@NonNull Context context) {
        // 获取配置信息，进行一些初始化的设置
        PropertiesUtil.init(context);
        String tag = PropertiesUtil.getProperty("tag");
        String isShowLog = PropertiesUtil.getProperty("isShowLog");
        Logger.init(TextUtils.isEmpty(tag) ? "TAG" : tag,
                !TextUtils.equals(isShowLog, "false"));
        AppCache.get().init(context, tag);
        Logger.i("LibBaseInitializer create: ");
        return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
//        List<Class<? extends Initializer<?>>> list = new ArrayList<>();
//        list.add(OtherInitializer.class);
        return Collections.emptyList();
    }

}

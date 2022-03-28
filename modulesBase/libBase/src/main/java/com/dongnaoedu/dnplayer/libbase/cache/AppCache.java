package com.dongnaoedu.dnplayer.libbase.cache;

import android.content.Context;

import com.dongnaoedu.dnplayer.libbase.utils.FileUtil;
import com.dongnaoedu.dnplayer.libbase.utils.Logger;

import java.io.File;

/**
 * App频繁用到文件缓存，在这初始化，操作和管理等
 */
public class AppCache {

    private ACache aCache;

    private final static class Holder {
        static AppCache INSTANCE = new AppCache();
    }

    public static AppCache get() {
        return Holder.INSTANCE;
    }

    public void init(Context context, String cacheName) {
        // 初始化Preferences
        Preferences.init(context);
        // 初始化App文件夹
        initAppDir(context, cacheName);
        // 初始化ACache
        aCache = ACache.get(context, cacheName);
    }

    public void initAppDir(Context context, String cacheName) {
        File mainDir = new File(context.getCacheDir().getAbsolutePath(), cacheName);
        File fileDir = new File(mainDir.getAbsolutePath(), "file");
        File tempDir = new File(mainDir.getAbsolutePath(), "temp");
        FileUtil.makeDir(mainDir);
        FileUtil.makeDir(fileDir);
        FileUtil.makeDir(tempDir);
        // 每次初始化时删除临时文件
        FileUtil.deleteDirFiles(tempDir);
        Logger.i("FileUtil initAppDir: " + mainDir.getAbsolutePath());
    }

    public void save(String key, String value) {
        aCache.put(key, value);
    }

    public String getString(String key) {
        return aCache.getAsString(key);
    }

    public void remove(String key) {
        aCache.remove(key);
    }

}
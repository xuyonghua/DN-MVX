package com.dongnaoedu.dnplayer.common.base;

import android.app.Application;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dongnaoedu.dnplayer.common.utils.DisplayUtil;
import com.dongnaoedu.dnplayer.common.utils.ToastUtil;
import com.dongnaoedu.dnplayer.libbase.utils.Logger;
import com.dongnaoedu.dnplayer.libbase.utils.PropertiesUtil;

/**
 * 做一些基础的初始化操作
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i("BaseApplication onCreate: ");
        String isRelease = PropertiesUtil.getProperty("isRelease");
        if (!TextUtils.equals(isRelease, "true")) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        DisplayUtil.init(this);
        ToastUtil.init(this);
    }
}

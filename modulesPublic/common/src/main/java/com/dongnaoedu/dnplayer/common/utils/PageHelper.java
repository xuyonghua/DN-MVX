package com.dongnaoedu.dnplayer.common.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dongnaoedu.dnplayer.common.constant.SimplePage;
import com.dongnaoedu.dnplayer.common.constant.RoutePath;

public class PageHelper {

    public static void showActivity(String path) {
        ARouter.getInstance().build(path).navigation();
    }

    public static void showSimplePage(SimplePage simplePage) {
        ARouter.getInstance().build(RoutePath.SIMPLE_PAGE_ACTIVITY)
                .withString("title", simplePage.getTitle())
                .withString("path", simplePage.getPath())
                .navigation();
    }

}

package com.dongnaoedu.dnplayer.common.utils;

import android.view.View;

public class ViewUtil {

    /**
     * 多个控件同时设置公用的OnClickListener
     *
     * @param listener
     * @param views
     */
    public static void setOnClick(View.OnClickListener listener, View... views) {
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }
}

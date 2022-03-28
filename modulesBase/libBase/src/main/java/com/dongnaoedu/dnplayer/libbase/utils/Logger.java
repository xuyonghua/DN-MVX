package com.dongnaoedu.dnplayer.libbase.utils;

import android.util.Log;

/**
 * 简单的处理下日志： TAG, Log开关
 */
public class Logger {

    private static String TAG = "TAG";

    private static boolean SHOW_LOG = true;

    public static void init(String tag, boolean isShowLog) {
        TAG = tag;
        SHOW_LOG = isShowLog;
    }

    public static void i(String str) {
        if (SHOW_LOG) {
            Log.i(TAG, str);
        }
    }

    public static void i(String tag, String str) {
        if (SHOW_LOG) {
            Log.i(tag, str);
        }
    }

    public static void v(String str) {
        if (SHOW_LOG) {
            Log.v(TAG, str);
        }
    }

    public static void v(String tag, String str) {
        if (SHOW_LOG) {
            Log.v(tag, str);
        }
    }

    public static void d(String str) {
        if (SHOW_LOG) {
            Log.d(TAG, str);
        }
    }

    public static void d(String tag, String str) {
        if (SHOW_LOG) {
            Log.d(tag, str);
        }
    }

    public static void w(String str) {
        if (SHOW_LOG) {
            Log.w(TAG, str);
        }
    }

    public static void w(String tag, String str) {
        if (SHOW_LOG) {
            Log.w(tag, str);
        }
    }

    public static void e(String str) {
        if (SHOW_LOG) {
            Log.e(TAG, str);
        }
    }

    public static void e(String tag, String str) {
        if (SHOW_LOG) {
            Log.e(tag, str);
        }
    }
}

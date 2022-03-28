package com.dongnaoedu.dnplayer.common.utils;

import android.content.Context;

import com.dongnaoedu.dnplayer.common.widget.LoadingDialog;

/**
 * 显示仿IOS的Loading Dialog
 */
public class DialogUtil {

    public static LoadingDialog showLoadingMsg(Context context, String message, int iconRes, int duration) {
        LoadingDialog dialog = new LoadingDialog(context);
        dialog.setType(LoadingDialog.TYPE_MSG)
                .setMessage(message)
                .setIcon(iconRes);
        dialog.dismissDelayed(duration);
        return dialog;
    }

    public static LoadingDialog showLoading(Context context, String message) {
        LoadingDialog dialog = new LoadingDialog(context);
        dialog.setType(LoadingDialog.TYPE_LOADING)
                .setMessage(message);
        return dialog;
    }

}
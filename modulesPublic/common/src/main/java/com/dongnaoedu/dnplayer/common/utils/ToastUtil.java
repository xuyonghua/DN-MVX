package com.dongnaoedu.dnplayer.common.utils;

import android.app.Application;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.dongnaoedu.dnplayer.common.R;

public class ToastUtil {

    public static void init(Application application) {
        com.hjq.toast.ToastUtils.init(application);
        com.hjq.toast.ToastUtils.setView(R.layout.view_toast);
        com.hjq.toast.ToastUtils.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, DisplayUtil.dp2px(125));
        com.hjq.toast.ToastUtils.getToast().setDuration(Toast.LENGTH_SHORT);
    }

    public static void showToast(int message) {
        if (message <= 0) return;
        com.hjq.toast.ToastUtils.show(message);
    }

    public static void showToast(String message) {
        if (TextUtils.isEmpty(message)) return;
        com.hjq.toast.ToastUtils.show(message);
    }

    /**
     * 显示自定义的toast
     *
     * @param message  toast内容
     * @param duration toast延迟
     * @param gravity
     */
    /*public static void showToast(String message, int duration, int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastMsg)
                    || Math.abs(time - lastTime) > 2000) {
                if (toast == null) {
                    toast = new Toast(context);
                    toast.setGravity(gravity, 0, *//*DisplayUtils.dp2px(100)*//*0);
                    toast.setDuration(duration);
                    // 设置自定义布局
                    View view = LayoutInflater.from(context).inflate(
                            R.layout.view_toast, null);
                    toast.setView(view);
                }
                TextView textView = toast.getView().findViewById(R.id.toastTV);
                textView.setText(message);
                toast.show();
                // 更新为最近类容
                lastMsg = message;
                lastTime = System.currentTimeMillis();
            }
        }
    }*/

}
package com.dongnaoedu.dnplayer.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dongnaoedu.dnplayer.common.R;

/**
 * 仿IOS Loading Dialog
 */
public class LoadingDialog extends Dialog {

    public final static int TYPE_LOADING = 1;
    public final static int TYPE_MSG = 2;

    private Handler handler;

    private View mLayout;
    private TextView tvMsg;
    private ImageView ivIcon;
    private ProgressBar pbLoading;

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialogTheme);
        init(context);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context) {
        getWindow().setDimAmount(0f);
        handler = new Handler(context.getMainLooper());
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = inflater.inflate(R.layout.layout_loading_dialog, null, false);
        tvMsg = mLayout.findViewById(R.id.tv_msg);
        ivIcon = mLayout.findViewById(R.id.iv_icon);
        pbLoading = mLayout.findViewById(R.id.pb_loading);
        setContentView(mLayout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        setCancelable(true);//用户可以点击后退键关闭 Dialog
        setCanceledOnTouchOutside(false);//用户可以点击外部来关闭
    }

    /**
     * 延时关闭
     *
     * @param duration
     */
    public void dismissDelayed(int duration) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingDialog.this.dismiss();
            }
        }, duration);
    }

    public LoadingDialog setTextColor(int color) {
        tvMsg.setTextColor(color);
        return this;
    }

    public LoadingDialog setIcon(int resId) {
        ivIcon.setImageResource(resId);
        return this;
    }

    public LoadingDialog setIcon(Bitmap bitmap) {
        ivIcon.setImageBitmap(bitmap);
        return this;
    }

    public LoadingDialog setMessage(String message) {
        tvMsg.setText(message);
        return this;
    }

    public LoadingDialog setCancel(boolean b) {
        super.setCancelable(b);
        return this;
    }

    public LoadingDialog setCancelOnTouchOutside(boolean b) {
        super.setCanceledOnTouchOutside(b);
        return this;
    }

    public String getMessage() {
        String msg = "";
        if (tvMsg != null) {
            msg = tvMsg.getText().toString();
        }
        return msg;
    }

    /**
     * 禁止手动取消
     *
     * @return
     */
    public LoadingDialog setCancelDisabled() {
        super.setCanceledOnTouchOutside(false);
        super.setCancelable(false);
        return this;
    }

    /**
     * 设置为loading还是msg对话框
     *
     * @param type
     * @return
     */
    public LoadingDialog setType(int type) {
        switch (type) {
            case TYPE_LOADING:
                pbLoading.setVisibility(View.VISIBLE);
                ivIcon.setVisibility(View.GONE);
                break;
            case TYPE_MSG:
                pbLoading.setVisibility(View.GONE);
                ivIcon.setVisibility(View.VISIBLE);
                break;
        }
        return this;
    }

}
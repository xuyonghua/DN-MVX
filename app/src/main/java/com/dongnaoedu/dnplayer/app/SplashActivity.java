package com.dongnaoedu.dnplayer.app;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dongnaoedu.dnplayer.common.constant.RoutePath;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 启动页Activity
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        skip();
    }

    private void skip() {
        ARouter.getInstance().build(RoutePath.MAIN_ACTIVITY).navigation();
        finish();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
}
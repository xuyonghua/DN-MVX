package com.dongnaoedu.dnplayer.main;

import com.dongnaoedu.dnplayer.common.base.BaseApplication;

import dagger.hilt.android.HiltAndroidApp;

/**
 * 切换到debug模式下需要使用该Application，Hilt才能正常使用
 */
@HiltAndroidApp
public class MainDebugApplication extends BaseApplication {

}

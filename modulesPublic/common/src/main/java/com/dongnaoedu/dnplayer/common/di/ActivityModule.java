package com.dongnaoedu.dnplayer.common.di;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.fragment.app.FragmentActivity;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;

@Module
@InstallIn(ActivityComponent.class)
public class ActivityModule {

    @ActivityScoped
    @Provides
    public RxPermissions provideRxPermission(Activity activity) {
        return new RxPermissions((FragmentActivity) activity);
    }

}

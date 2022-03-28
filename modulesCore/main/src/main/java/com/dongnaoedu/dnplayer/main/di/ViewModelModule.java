package com.dongnaoedu.dnplayer.main.di;

import android.app.Activity;

import com.dongnaoedu.dnplayer.common.mvvm.BaseViewModel;
import com.dongnaoedu.dnplayer.main.vm.MusicViewModel;
import com.dongnaoedu.dnplayer.main.vm.TestViewModel;
import com.dongnaoedu.dnplayer.main.vm.UserInfoViewModel;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@InstallIn(ActivityComponent.class)
@Module
public class ViewModelModule {

    @Provides
    MusicViewModel provideMusicViewModel(Activity activity) {
        return createViewModel((ComponentActivity) activity, MusicViewModel.class);
    }

    @Provides
    TestViewModel provideTestViewModel(Activity activity) {
        return createViewModel((ComponentActivity) activity, TestViewModel.class);
    }

    @Provides
    UserInfoViewModel provideUserInfoViewModel(Activity activity) {
        return createViewModel((ComponentActivity) activity, UserInfoViewModel.class);
    }

    private <T extends BaseViewModel> T createViewModel(ComponentActivity activity, Class<T> clz) {
        T t = new ViewModelProvider(activity).get(clz);
        t.attach(activity);
        return t;
    }

}

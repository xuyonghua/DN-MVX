package com.dongnaoedu.dnplayer.main.di;

import com.dongnaoedu.dnplayer.common.data.ApiService;
import com.dongnaoedu.dnplayer.main.contract.MusicContract;
import com.dongnaoedu.dnplayer.main.presenter.MusicPresenter;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;

@InstallIn(FragmentComponent.class)
@Module
public class PresenterModule {

    // 注入Presenter，约定的规范：所有的Presenter只注入到Fragment中
    @Provides
    MusicPresenter provideMusicPresenter(Fragment fragment, ApiService apiService) {
        MusicContract.View view = (MusicContract.View) fragment;
        MusicPresenter musicPresenter = new MusicPresenter(apiService);
        musicPresenter.attach(view);
        return musicPresenter;
    }

}

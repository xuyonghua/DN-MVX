package com.dongnaoedu.dnplayer.main.contract;

import com.dongnaoedu.dnplayer.common.data.entity.MusicInfo;
import com.dongnaoedu.dnplayer.common.mvp.IPresenter;
import com.dongnaoedu.dnplayer.common.mvp.IView;
import com.dongnaoedu.dnplayer.libbase.net.exception.ApiException;

import java.util.List;

public interface MusicContract {

    interface View extends IView {

        void onLoadMusicSuccess(List<MusicInfo> list);

        void onLoadMusicFailure(ApiException e);
    }

    interface Presenter extends IPresenter<View> {

        void loadMusics(int page);
    }

}

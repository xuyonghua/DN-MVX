package com.dongnaoedu.dnplayer.main.presenter;

import com.dongnaoedu.dnplayer.common.data.ApiService;
import com.dongnaoedu.dnplayer.common.data.entity.ListData;
import com.dongnaoedu.dnplayer.common.data.entity.MusicInfo;
import com.dongnaoedu.dnplayer.common.mvp.BasePresenter;
import com.dongnaoedu.dnplayer.libbase.net.RequestModel;
import com.dongnaoedu.dnplayer.libbase.net.exception.ApiException;
import com.dongnaoedu.dnplayer.libbase.net.response.ICallback;
import com.dongnaoedu.dnplayer.main.contract.MusicContract;

import javax.inject.Inject;

public class MusicPresenter extends BasePresenter<MusicContract.View>
        implements MusicContract.Presenter {

    ApiService apiService;

    //@Inject
    public MusicPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void loadMusics(int page) {
        //mView.showLoading("");
        RequestModel.request(apiService.getMusics(10, page),
                mView,
                new ICallback<ListData<MusicInfo>>() {
                    @Override
                    public void onSuccess(ListData<MusicInfo> data) {
                        mView.onLoadMusicSuccess(data.getRecords());
                        //mView.dismissLoading();
                    }

                    @Override
                    public void onFailure(ApiException e) {
                        mView.onLoadMusicFailure(e);
                        //mView.dismissLoading();
                    }
                });
    }

}

package com.dongnaoedu.dnplayer.main.vm;

import com.dongnaoedu.dnplayer.common.data.ApiService;
import com.dongnaoedu.dnplayer.common.data.entity.ListData;
import com.dongnaoedu.dnplayer.common.data.entity.MusicInfo;
import com.dongnaoedu.dnplayer.common.mvvm.BaseViewModel;
import com.dongnaoedu.dnplayer.libbase.net.RequestModel;
import com.dongnaoedu.dnplayer.libbase.net.exception.ApiException;
import com.dongnaoedu.dnplayer.libbase.net.response.ICallback;

import java.util.List;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

public class MusicViewModel extends BaseViewModel {

    public MutableLiveData<List<MusicInfo>> musicsData = new MutableLiveData<>();

    ApiService apiService;

    @ViewModelInject
    public MusicViewModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public void loadMusics(int page) {
        RequestModel.request(apiService.getMusics(10, page), lifecycleOwner, new ICallback<ListData<MusicInfo>>() {
            @Override
            public void onSuccess(ListData<MusicInfo> data) {
                musicsData.setValue(data.getRecords());
                // 线程切换
                //musicsData.postValue();
            }

            @Override
            public void onFailure(ApiException e) {

            }
        });
    }

}

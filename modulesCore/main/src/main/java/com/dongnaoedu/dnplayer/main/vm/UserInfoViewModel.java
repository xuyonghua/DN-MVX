package com.dongnaoedu.dnplayer.main.vm;

import com.dongnaoedu.dnplayer.common.data.ApiService;
import com.dongnaoedu.dnplayer.common.data.entity.UserInfo;
import com.dongnaoedu.dnplayer.common.mvvm.BaseViewModel;
import com.dongnaoedu.dnplayer.libbase.net.RequestModel;
import com.dongnaoedu.dnplayer.libbase.net.exception.ApiException;
import com.dongnaoedu.dnplayer.libbase.net.response.ICallback;

import androidx.databinding.ObservableField;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

public class UserInfoViewModel extends BaseViewModel {

    public ObservableField<String> userName = new ObservableField<>();

    public MutableLiveData<UserInfo> userData = new MutableLiveData<>();

    ApiService apiService;

    @ViewModelInject
    public UserInfoViewModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public void loadUserInfo() {
        RequestModel.request(apiService.getUserInfo("1"),
                lifecycleOwner,
                new ICallback<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo data) {
                        userData.setValue(data);
                        userName.set("手动userName");
                    }

                    @Override
                    public void onFailure(ApiException e) {

                    }
                });
    }

}

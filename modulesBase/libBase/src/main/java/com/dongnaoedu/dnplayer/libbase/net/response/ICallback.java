package com.dongnaoedu.dnplayer.libbase.net.response;

import com.dongnaoedu.dnplayer.libbase.net.exception.ApiException;

public interface ICallback<T> {

    void onSuccess(T data);

    void onFailure(ApiException e);
}

package com.dongnaoedu.dnplayer.common.mvp;

import androidx.lifecycle.LifecycleOwner;

public interface IView extends LifecycleOwner {

    void showLoading(String msg);

    void dismissLoading();

    void showToast(String msg);

    void showLoadingLayout();

    void showSuccessLayout();

    void showErrorLayout();

    void showEmptyLayout();

}

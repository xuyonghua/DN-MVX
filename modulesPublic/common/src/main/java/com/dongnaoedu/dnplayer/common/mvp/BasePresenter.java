package com.dongnaoedu.dnplayer.common.mvp;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * BasePresenter关联了生命周期，自动进行销毁管理
 *
 * @param <V>
 */
public class BasePresenter<V extends IView>
        implements IPresenter<V>, LifecycleObserver {

    protected V mView;

    @Override
    public void attach(V view) {
        mView = view;
        // 关联生命周期
        mView.getLifecycle().addObserver(this);
    }

    @Override
    public void detach() {
        if (mView != null) {
            mView.getLifecycle().removeObserver(this);
            mView = null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        detach();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(LifecycleOwner owner) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(LifecycleOwner owner) {

    }

}

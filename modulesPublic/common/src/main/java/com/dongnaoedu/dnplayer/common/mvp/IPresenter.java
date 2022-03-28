package com.dongnaoedu.dnplayer.common.mvp;

/**
 * Presenter实现目的：分层控制，复用
 *
 * @param <V>
 */
public interface IPresenter<V extends IView> {

    void attach(V view);

    void detach();

}

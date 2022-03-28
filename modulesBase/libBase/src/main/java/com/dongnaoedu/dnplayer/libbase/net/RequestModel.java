package com.dongnaoedu.dnplayer.libbase.net;

import com.dongnaoedu.dnplayer.libbase.net.exception.ApiException;
import com.dongnaoedu.dnplayer.libbase.net.response.ICallback;
import com.dongnaoedu.dnplayer.libbase.net.response.IResponse;
import com.dongnaoedu.dnplayer.libbase.net.response.ResponseTransformer;

import androidx.lifecycle.LifecycleOwner;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 统一请求的封装
 * （不一定要这么写，看个人爱好）
 */
public class RequestModel {

    public static <T> void request(Observable<? extends IResponse<T>> o,
                                   LifecycleOwner lifecycleOwner,
                                   ICallback<T> callback) {
        Disposable d = o.compose(ResponseTransformer.obtain(lifecycleOwner))
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        callback.onSuccess(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onFailure(ApiException.handleException(throwable));
                    }
                });
    }

}

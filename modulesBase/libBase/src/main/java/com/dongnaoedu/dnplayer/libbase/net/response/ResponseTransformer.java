package com.dongnaoedu.dnplayer.libbase.net.response;

import com.dongnaoedu.dnplayer.libbase.net.exception.ApiException;
import com.dongnaoedu.dnplayer.libbase.utils.ReflectUtil;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Observable<IResponse<T>> -> Observable<T>
 * 实现：
 * 1. 对线程进行切换，达到代码复用的目标
 * 2. 对RxJava生命周期管理，防止内存泄漏
 * 3. 对响应数据统一处理，获取到真正想用的data，进行业务处理
 */
public class ResponseTransformer<T> implements ObservableTransformer<IResponse<T>, T>, LifecycleObserver {

    final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @NonNull
    @Override
    public ObservableSource<T> apply(@NonNull Observable<IResponse<T>> upstream) {
        return upstream.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                compositeDisposable.add(disposable);
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends IResponse<T>>>() {
            @Override
            public ObservableSource<? extends IResponse<T>> apply(@NonNull Throwable throwable) throws Exception {
                // 出现异常统一处理 (非业务性的异常)
                return Observable.error(ApiException.handleException(throwable));
            }
        }).flatMap(new Function<IResponse<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(@NonNull IResponse<T> response) throws Exception {
                // 对响应数据统一处理
                if (response.isSuccess()) {
                    if (response.getData() != null) {
                        return Observable.just(response.getData());
                    } else {
                        // 业务请求可能成功了，但是data是NULL
                        // 通过反射手动创建data，这个data一般是没有实际用途
                        Class<?> clz = ReflectUtil.analysisClassInfo(response);
                        T object = (T) clz.newInstance();
                        return Observable.just(object);
                    }
                }
                return Observable.error(new ApiException(response.getCode(), response.getMsg()));
            }
        }).subscribeOn(Schedulers.io())//指定事件产生的线程(请求的线程)
                .observeOn(AndroidSchedulers.mainThread());// 指定事件处理的线程 (响应的线程)
    }

    public static <T> ResponseTransformer<T> obtain(LifecycleOwner lifecycleOwner) {
        ResponseTransformer<T> transformer = new ResponseTransformer<>();
        lifecycleOwner.getLifecycle().addObserver(transformer);
        return transformer;
    }

}

package com.ly.supermvp.server;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by lip on 2018/4/19.
 * e:lip@magicare.me
 */

public class RetryWithDelay implements Function<Observable<Throwable>, ObservableSource<?>> {
    public final String TAG = this.getClass().getSimpleName();
    private final int maxRetries;
    private final int retryDelaySecond;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelaySecond) {
        this.maxRetries = maxRetries;
        this.retryDelaySecond = retryDelaySecond;
    }

    public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
                if(++RetryWithDelay.this.retryCount <= RetryWithDelay.this.maxRetries) {
                    Log.d(RetryWithDelay.this.TAG, "get error, it will try after " + RetryWithDelay.this.retryDelaySecond + " second, retry count " + RetryWithDelay.this.retryCount);
                    return Observable.timer((long)RetryWithDelay.this.retryDelaySecond, TimeUnit.SECONDS);
                } else {
                    return Observable.error(throwable);
                }
            }
        });
    }
}

package com.ly.supermvp.server;


import com.ly.supermvp.model.entity.OpenApiResponse;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by barry on 2018/3/23.
 * 网络请求转换器
 */

public class NetTransformer<T extends OpenApiResponse<E>, E> implements ObservableTransformer<T, E> {

    private LifecycleProvider mLifecycleProvider;
    private RxDialogFragment mDialogLifecycleProvider;
    //是否需要重试
    private boolean mIsNeedRetry = true;

    /**
     * 只绑定Activity或Fragment生命周期
     *
     * @param view
     */
    public NetTransformer(LifecycleProvider view) {
        this.mLifecycleProvider = view;
    }

    /**
     * 只绑定Activity或Fragment生命周期
     *
     * @param view
     */
    public NetTransformer(LifecycleProvider view, boolean isNeedRetry) {
        this.mLifecycleProvider = ((LifecycleProvider) view);
        this.mIsNeedRetry = isNeedRetry;
    }

    /**
     * 绑定Activity或Fragment生命周期以及dialog的生命周期
     *
     * @param view
     * @param mDialogLifecycleProvider
     */
    public NetTransformer(LifecycleProvider view, RxDialogFragment mDialogLifecycleProvider) {
        this.mLifecycleProvider = ((LifecycleProvider) view);
        this.mDialogLifecycleProvider = mDialogLifecycleProvider;
    }


    private Function<T, E> mMapFunction = new Function<T, E>() {
        @Override
        public E apply(T t) throws Exception {
            if (t == null)
                throw new Exception("解析错误");
            if (t.isSuccess()) {
                return t.getResult();
            } else {
                //服务端自定义的token错误状态码为200
                throw new Exception(t.getMessage());
            }
        }
    };

    @Override
    public ObservableSource apply(Observable upstream) {
        Observable observable = null;
        if (mIsNeedRetry) {
            observable = upstream
                    .subscribeOn(Schedulers.io())
//                    .retryWhen(new RetryWithDelay(3, 2))
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        ObservableSource observableSource = null;
        if (mDialogLifecycleProvider != null) {
            observableSource = observable.map(mMapFunction)
                    .compose(mLifecycleProvider.bindToLifecycle())
                    .compose(mDialogLifecycleProvider.bindToLifecycle());
        } else if (mLifecycleProvider != null) {
            observableSource = observable.map(mMapFunction)
                    .compose(mLifecycleProvider.bindToLifecycle());
        } else {
            observableSource = observable.map(mMapFunction);
        }

        return observableSource;
    }
}
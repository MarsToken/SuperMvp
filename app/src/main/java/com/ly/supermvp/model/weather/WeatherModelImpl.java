package com.ly.supermvp.model.weather;

import com.ly.supermvp.model.OnNetRequestListener;
import com.ly.supermvp.model.entity.ShowApiResponse;
import com.ly.supermvp.model.entity.ShowApiWeather;
import com.ly.supermvp.server.RetrofitService;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * <Pre>
 * 天气预报实现类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/1 14:48
 */
public class WeatherModelImpl implements WeatherModel {

    private LifecycleTransformer mLifecycleTransformer;

    public WeatherModelImpl(LifecycleTransformer mLifecycleTransformer) {
        this.mLifecycleTransformer = mLifecycleTransformer;
    }

    @Override
    public void netLoadWeatherWithLocation(String area, String needMoreDay, String needIndex,
                                           String needAlarm, String need3HourForcast,
                                           final OnNetRequestListener listener) {
        //使用RxJava响应Retrofit
        Observable<ShowApiResponse<ShowApiWeather>> observable = RetrofitService.getInstance().
                createAPI().getWeather(RetrofitService.getCacheControl(), area, needMoreDay,
                needIndex, needAlarm, need3HourForcast);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mLifecycleTransformer)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        listener.onStart();
                    }
                })
                .subscribe(new Observer<ShowApiResponse<ShowApiWeather>>() {
                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e);
                        listener.onFinish();
                    }

                    @Override
                    public void onComplete() {
                        listener.onFinish();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShowApiResponse<ShowApiWeather> showApiWeatherShowApiResponse) {
                        if(showApiWeatherShowApiResponse.showapi_res_body.now == null){
                            listener.onFailure(new Exception(showApiWeatherShowApiResponse.showapi_res_code));
                        }else {
                            listener.onSuccess(showApiWeatherShowApiResponse.showapi_res_body);
                        }
                    }
                });
    }
}

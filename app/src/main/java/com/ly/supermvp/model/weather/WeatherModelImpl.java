package com.ly.supermvp.model.weather;

import com.ly.supermvp.common.BizInterface;
import com.ly.supermvp.model.OnNetRequestListener;
import com.ly.supermvp.model.entity.OpenApiResponse;
import com.ly.supermvp.model.entity.OpenApiWeather;
import com.ly.supermvp.model.entity.ShowApiResponse;
import com.ly.supermvp.model.entity.ShowApiWeather;
import com.ly.supermvp.server.RetrofitService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;


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
                        if(showApiWeatherShowApiResponse.getShowapi_res_body().getNow() == null){
                            listener.onFailure(new Exception(showApiWeatherShowApiResponse.getShowapi_res_code()));
                        }else {
                            listener.onSuccess(showApiWeatherShowApiResponse.getShowapi_res_body());
                        }
                    }
                });
    }

    @Override
    public Observable<OpenApiResponse<OpenApiWeather>> netGetWeather(String cityName) {
        //临时切换baseurl
        RetrofitUrlManager.getInstance().putDomain(BizInterface.DOMAIN_OPEN_API, BizInterface.OPEN_API);
        return RetrofitService.getInstance().
                createAPI().getWeather(RetrofitService.getCacheControl(), cityName);
    }
}

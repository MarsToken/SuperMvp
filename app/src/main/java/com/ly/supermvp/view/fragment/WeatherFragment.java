package com.ly.supermvp.view.fragment;

import android.text.TextUtils;
import android.view.View;

import com.ly.supermvp.R;
import com.ly.supermvp.adapter.WeatherAdapter;
import com.ly.supermvp.delegate.WeatherFragmentDelegate;
import com.ly.supermvp.model.OnNetRequestListener;
import com.ly.supermvp.model.entity.OpenApiPicture;
import com.ly.supermvp.model.entity.OpenApiResponse;
import com.ly.supermvp.model.entity.OpenApiWeather;
import com.ly.supermvp.model.entity.ShowApiWeather;
import com.ly.supermvp.model.weather.WeatherModel;
import com.ly.supermvp.model.weather.WeatherModelImpl;
import com.ly.supermvp.mvp_frame.presenter.FragmentPresenter;
import com.ly.supermvp.server.NetTransformer;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <Pre>
 * 天气预报fragment
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/2/29 17:43
 * @see https://github.com/liuyanggithub/SuperMvp
 */
public class WeatherFragment extends FragmentPresenter<WeatherFragmentDelegate> implements View.OnClickListener {
    public static final String NEED_MORE_DAY = "1";
    public static final String NEED_INDEX = "1";
    public static final String NEED_ALARM = "1";
    public static final String NEED_3_HOUR_FORCAST = "1";

    private WeatherModel mWeatherModel;

    private List<OpenApiWeather.ForecastBean> mForecastBeans = new ArrayList<>();
    private WeatherAdapter mWeatherAdapter;

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    protected Class<WeatherFragmentDelegate> getDelegateClass() {
        return WeatherFragmentDelegate.class;
    }

    @Override
    protected void initData() {
        super.initData();
        mWeatherModel = new WeatherModelImpl();
        mWeatherAdapter = new WeatherAdapter(mForecastBeans);
        viewDelegate.initRecyclerView(mWeatherAdapter);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this, R.id.bt_weather);
    }

    /**
     * 获取天气预报
     */
    private void netWeather() {
        if(TextUtils.isEmpty(viewDelegate.getInputLocation())){
            viewDelegate.showSnackbar("输入为空");
            return;
        }
        mWeatherModel.netGetWeather(viewDelegate.getInputLocation()).compose(
                new NetTransformer<OpenApiResponse<OpenApiWeather>, OpenApiWeather>(this))
                .subscribe(new Observer<OpenApiWeather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        viewDelegate.showLoading();
                    }

                    @Override
                    public void onNext(OpenApiWeather openApiWeather) {
                        viewDelegate.showContent();
                        viewDelegate.closeSoftInput();

                        if (!openApiWeather.getForecast().isEmpty()) {
                            mForecastBeans.clear();
                            mForecastBeans.addAll(openApiWeather.getForecast());
                            mWeatherAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewDelegate.showContent();
                        viewDelegate.showSnackbar("请求错误");
                    }

                    @Override
                    public void onComplete() {
                        viewDelegate.showContent();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_weather:
                netWeather();
                break;
        }
    }
}

package com.ly.supermvp.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ly.supermvp.R;
import com.ly.supermvp.model.entity.OpenApiWeather;

import java.util.List;

/**
 * <Pre>
 *     天气列表适配器
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 * <p>
 * Create by 2019/4/12 15:50
 */
public class WeatherAdapter extends BaseQuickAdapter<OpenApiWeather.ForecastBean, BaseViewHolder> {
    public WeatherAdapter(@Nullable List<OpenApiWeather.ForecastBean> data) {
        super(R.layout.item_weather_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenApiWeather.ForecastBean item) {
        helper.setText(R.id.tv_date, item.getDate());
        helper.setText(R.id.tv_type, item.getType());
        helper.setText(R.id.tv_temperature_high, item.getHigh());
        helper.setText(R.id.tv_temperature_low, item.getLow());
        helper.setText(R.id.tv_wind_direction, item.getFengxiang());
    }
}

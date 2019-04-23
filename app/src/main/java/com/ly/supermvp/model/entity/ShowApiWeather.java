package com.ly.supermvp.model.entity;

/**
 * <Pre>
 *     天气
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/1 14:24
 */
public class ShowApiWeather {
    private ShowApiWeatherNormalInner f1;//后一天的天气预报
    private ShowApiWeatherNowInner now;//现在的天气预报

    public static class ShowApiWeatherNormalInner {
        private String day;//日期
        private String air_press;//气压
        private String sun_begin_end;//白天持续时间
        private String weekday;//星期几
        private String ziwaixian;//紫外线
        //白天
        private String day_air_temperature;//气温
        private String day_weather;//天气“晴雨”
        private String day_weather_code;//天气代码
        private String day_weather_pic;//天气图片
        private String day_wind_direction;//风向
        private String day_wind_power;//风力
        //晚上
        private String night_air_temperature;
        private String night_weather;
        private String night_weather_code;
        private String night_weather_pic;
        private String night_wind_direction;
        private String night_wind_power;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getAir_press() {
            return air_press;
        }

        public void setAir_press(String air_press) {
            this.air_press = air_press;
        }

        public String getSun_begin_end() {
            return sun_begin_end;
        }

        public void setSun_begin_end(String sun_begin_end) {
            this.sun_begin_end = sun_begin_end;
        }

        public String getWeekday() {
            return weekday;
        }

        public void setWeekday(String weekday) {
            this.weekday = weekday;
        }

        public String getZiwaixian() {
            return ziwaixian;
        }

        public void setZiwaixian(String ziwaixian) {
            this.ziwaixian = ziwaixian;
        }

        public String getDay_air_temperature() {
            return day_air_temperature;
        }

        public void setDay_air_temperature(String day_air_temperature) {
            this.day_air_temperature = day_air_temperature;
        }

        public String getDay_weather() {
            return day_weather;
        }

        public void setDay_weather(String day_weather) {
            this.day_weather = day_weather;
        }

        public String getDay_weather_code() {
            return day_weather_code;
        }

        public void setDay_weather_code(String day_weather_code) {
            this.day_weather_code = day_weather_code;
        }

        public String getDay_weather_pic() {
            return day_weather_pic;
        }

        public void setDay_weather_pic(String day_weather_pic) {
            this.day_weather_pic = day_weather_pic;
        }

        public String getDay_wind_direction() {
            return day_wind_direction;
        }

        public void setDay_wind_direction(String day_wind_direction) {
            this.day_wind_direction = day_wind_direction;
        }

        public String getDay_wind_power() {
            return day_wind_power;
        }

        public void setDay_wind_power(String day_wind_power) {
            this.day_wind_power = day_wind_power;
        }

        public String getNight_air_temperature() {
            return night_air_temperature;
        }

        public void setNight_air_temperature(String night_air_temperature) {
            this.night_air_temperature = night_air_temperature;
        }

        public String getNight_weather() {
            return night_weather;
        }

        public void setNight_weather(String night_weather) {
            this.night_weather = night_weather;
        }

        public String getNight_weather_code() {
            return night_weather_code;
        }

        public void setNight_weather_code(String night_weather_code) {
            this.night_weather_code = night_weather_code;
        }

        public String getNight_weather_pic() {
            return night_weather_pic;
        }

        public void setNight_weather_pic(String night_weather_pic) {
            this.night_weather_pic = night_weather_pic;
        }

        public String getNight_wind_direction() {
            return night_wind_direction;
        }

        public void setNight_wind_direction(String night_wind_direction) {
            this.night_wind_direction = night_wind_direction;
        }

        public String getNight_wind_power() {
            return night_wind_power;
        }

        public void setNight_wind_power(String night_wind_power) {
            this.night_wind_power = night_wind_power;
        }
    }

    public static class ShowApiWeatherNowInner {
        private String aqi;//污染指数
        private String sd;//湿度
        private String temperature;//气温
        private String temperature_time;//气温时间
        private String weather;//天气“晴雨”
        private String weather_code;//天气代码
        private String weather_pic;//天气图片
        private String wind_direction;//风向
        private String wind_power;//风力

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getSd() {
            return sd;
        }

        public void setSd(String sd) {
            this.sd = sd;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTemperature_time() {
            return temperature_time;
        }

        public void setTemperature_time(String temperature_time) {
            this.temperature_time = temperature_time;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeather_code() {
            return weather_code;
        }

        public void setWeather_code(String weather_code) {
            this.weather_code = weather_code;
        }

        public String getWeather_pic() {
            return weather_pic;
        }

        public void setWeather_pic(String weather_pic) {
            this.weather_pic = weather_pic;
        }

        public String getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(String wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getWind_power() {
            return wind_power;
        }

        public void setWind_power(String wind_power) {
            this.wind_power = wind_power;
        }
    }

    public ShowApiWeatherNormalInner getF1() {
        return f1;
    }

    public void setF1(ShowApiWeatherNormalInner f1) {
        this.f1 = f1;
    }

    public ShowApiWeatherNowInner getNow() {
        return now;
    }

    public void setNow(ShowApiWeatherNowInner now) {
        this.now = now;
    }
}

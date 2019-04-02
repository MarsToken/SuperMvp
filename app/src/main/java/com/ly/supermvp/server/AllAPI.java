package com.ly.supermvp.server;

import com.ly.supermvp.common.BizInterface;
import com.ly.supermvp.model.entity.OpenApiResponse;
import com.ly.supermvp.model.entity.ShowApiResponse;
import com.ly.supermvp.model.news.ShowApiNews;
import com.ly.supermvp.model.pictures.OpenApiPicture;
import com.ly.supermvp.model.weather.ShowApiWeather;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * <Pre>
 * 易源api
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p>
 *          Create by 2017/1/18 15:41
 */

public interface AllAPI {
    /**
     * 新闻列表
     * @param cacheControl 缓存控制
     * @param appId 易源appid
     * @param key 易源密钥
     * @param page 页数
     * @param channelId 频道id
     * @param channelName 名称
     * @return
     */
    @GET(BizInterface.NEWS_URL)
    @Headers({"apikey: " + BizInterface.API_KEY, BizInterface.DOMAIN + BizInterface.DOMAIN_SHOW_API})
    Call<ShowApiResponse<ShowApiNews>> getNewsList(@Header("Cache-Control") String cacheControl,
                                                   @Query("showapi_appid") String appId,
                                                   @Query("showapi_sign") String key,
                                                   @Query("page") int page,
                                                   @Query("channelId") String channelId,//新闻频道id，必须精确匹配
                                                   @Query("channelName") String channelName);//新闻频道名称，可模糊匹配


    /**
     * 美图大全
     * @param page 页数
     * @param count 每页请求数目
     * @return
     */
    @Headers({BizInterface.DOMAIN + BizInterface.DOMAIN_OPEN_API})
    @FormUrlEncoded
    @POST(BizInterface.OPEN_API_PICTURES_URL)
    Observable<OpenApiResponse<List<OpenApiPicture>>> getPictures(@Header("Cache-Control") String cacheControl,
                                                                  @Field("page") int page,
                                                                  @Field("count") int count);

    /**
     * 天气预报
     * @param area 地区名称，比如北京
     * @param needMoreDay 是否需要返回7天数据中的后4天。1为返回，0为不返回。
     * @param needIndex 是否需要返回指数数据，比如穿衣指数、紫外线指数等。1为返回，0为不返回。
     * @param needAlarm 是否需要天气预警。1为需要，0为不需要。
     * @param need3HourForcast 是否需要当天每3小时1次的天气预报列表。1为需要，0为不需要。
     * @return
     */
    @GET(BizInterface.WEATHER_URL)
    @Headers("apikey: " + BizInterface.API_KEY)
    Observable<ShowApiResponse<ShowApiWeather>> getWeather(@Header("Cache-Control") String cacheControl,
                                                           @Query("area") String area,
                                                           @Query("needMoreDay") String needMoreDay,
                                                           @Query("needIndex") String needIndex,
                                                           @Query("needAlarm") String needAlarm,
                                                           @Query("need3HourForcast") String need3HourForcast);

}

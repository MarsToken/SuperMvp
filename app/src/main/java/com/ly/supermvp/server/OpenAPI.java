package com.ly.supermvp.server;

import com.ly.supermvp.common.BizInterface;
import com.ly.supermvp.model.entity.OpenApiResponse;
import com.ly.supermvp.model.pictures.OpenApiPicture;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * <Pre>
 *     OpenAPI
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 * <p>
 * Create by 2019/3/28 20:19
 */
public interface OpenAPI {
    @FormUrlEncoded
    @POST(BizInterface.OPEN_API_PICTURES_URL)
    Observable<OpenApiResponse<List<OpenApiPicture>>> getPictures(@Header("Cache-Control") String cacheControl,
                                                                  @Field("page") int page,
                                                                  @Field("count") int count);
}

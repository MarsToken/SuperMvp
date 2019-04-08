package com.ly.supermvp.model.pictures;

import com.ly.supermvp.model.entity.OpenApiPicture;
import com.ly.supermvp.model.entity.OpenApiResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * <Pre>
 * 图片大全数据接口
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 16:02
 */
public interface PicturesModel {
    Observable<OpenApiResponse<List<OpenApiPicture>>> netLoadPicturesByOpenApi(int page, int count);
}

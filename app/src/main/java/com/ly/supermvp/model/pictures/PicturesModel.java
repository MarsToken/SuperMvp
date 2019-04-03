package com.ly.supermvp.model.pictures;

import com.ly.supermvp.model.OnNetRequestListener;
import com.ly.supermvp.model.entity.OpenApiPicture;

import java.util.List;

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
    void netLoadPicturesByOpenApi(int page, int count, OnNetRequestListener<List<OpenApiPicture>> listener);
}

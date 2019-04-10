package com.ly.supermvp.model.news;

import com.ly.supermvp.model.entity.ShowApiNews;
import com.ly.supermvp.model.entity.ShowApiResponse;

import retrofit2.Call;

/**
 * <Pre>
 *     新闻数据加载接口
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/28 14:48
 */
public interface NewsModel {
    /**
     * 加载新闻列表
     * @param page 页数
     * @param channelId 频道id 来自api
     * @param channelName 频道名称
     */
    Call<ShowApiResponse<ShowApiNews>> netLoadNewsList(int page, String channelId, String channelName);
}

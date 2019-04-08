package com.ly.supermvp.model.entity;

/**
 * <Pre>
 *  https://www.apiopen.top
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 * <p>
 * Create by 2019/3/28 20:09
 */
public class OpenApiResponse<T> {
    public static final int SUCCESS = 200;
    public int code;
    public String message;
    public T result;

    /**
     * 判断是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return SUCCESS == code;
    }
}

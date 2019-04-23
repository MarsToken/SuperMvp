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
    private int code;
    private String message;
    private T result;

    /**
     * 判断是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return SUCCESS == code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

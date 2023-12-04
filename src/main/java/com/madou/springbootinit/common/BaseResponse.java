package com.madou.springbootinit.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    /**
     * 响应码
     */
    private int errno;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回信息
     */
    private String errmsg;

    public BaseResponse(int code, T data, String message) {
        this.errno = code;
        this.data = data;
        this.errmsg = message;
    }

    public BaseResponse(int code, T data) {
        this(code,data,"");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}

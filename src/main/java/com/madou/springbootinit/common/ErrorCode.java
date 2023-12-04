package com.madou.springbootinit.common;

/**
 * 自定义错误码
 */
public enum ErrorCode {
    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN_ERROR(501, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    TOO_MANY_REQUESTS(42900, "请求频繁"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败");

    /**
     * 状态码
     */
    private final int errno;

    /**
     * 信息
     */
    private final String errmsg;

    ErrorCode(int code, String message) {
        this.errno = code;
        this.errmsg = message;
    }

    public int getCode() {
        return errno;
    }

    public String getMessage() {
        return errmsg;
    }
}

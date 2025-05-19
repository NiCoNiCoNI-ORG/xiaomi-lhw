package com.xiaom.bms.common;

public enum ResponseResultType {
    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源未找到"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    private final int code;
    private final String defaultMessage;

    ResponseResultType(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public int getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
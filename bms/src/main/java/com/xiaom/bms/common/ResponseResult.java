package com.xiaom.bms.common;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    private int code; // 状态码
    private String message; // 请求信息
    private T data; // 响应数据

    public ResponseResult() {}

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 请求成功，无数据返回
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(ResponseResultType.SUCCESS.getCode(), "ok", null);
    }

    // 请求成功，有数据返回
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResponseResultType.SUCCESS.getCode(), "ok", data);
    }

    // 请求失败，带错误信息
    public static <T> ResponseResult<T> fail(ResponseResultType type, String message) {
        return new ResponseResult<>(type.getCode(), message, null);
    }

    // Getter 和 Setter 方法
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}




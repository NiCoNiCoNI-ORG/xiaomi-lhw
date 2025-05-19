package com.bench.bms.common.model;

import lombok.Data;

/**
 * @Author bench
 * @Date 2024/06/16 14:22
 **/
@Data
public class BaseRes<T> {

    public static final int STATUS_SUCCESS = 200;
    public static final int REQUEST_ERROR = 400;
    public static final int STATUS_ERROR = 500;


    private int status;
    private String msg;
    private T data;

    public BaseRes(int status, String msg, T data) {
        this.setStatus(status);
        this.setMsg(msg);
        this.setData(data);
    }

    public static <T> BaseRes<T> success() {
        return new BaseRes<>(STATUS_SUCCESS, "ok", null);
    }

    public static <T> BaseRes<T> success(T data) {
        return new BaseRes<>(STATUS_SUCCESS, "ok", data);
    }

    public static <T> BaseRes<T> error(String msg) {
        return new BaseRes<>(STATUS_ERROR, msg, null);
    }

    public static <T> BaseRes<T> requestError(String msg) {
        return new BaseRes<>(REQUEST_ERROR, msg, null);
    }
}

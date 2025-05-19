package com.bench.bms.common.exception;

import lombok.Data;

/**
 * @Author bench
 * @Date 2024/06/24 14:03
 **/

@Data
public class BizException extends RuntimeException {
    private final String message;
    private final int code;

    public BizException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}


package com.bench.bms.common.exception;

import com.bench.bms.common.exception.exceptionsenum.InfraExceptionEnum;
import lombok.Getter;

/**
 * @Author bench
 * @Date 2024/06/24 14:43
 **/

@Getter
public class InfraException extends RuntimeException {
    private final int code;
    private final String message;

    public InfraException(InfraExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }
}


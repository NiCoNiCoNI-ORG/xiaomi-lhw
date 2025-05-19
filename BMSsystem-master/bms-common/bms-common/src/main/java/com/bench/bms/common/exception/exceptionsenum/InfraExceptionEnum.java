package com.bench.bms.common.exception.exceptionsenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author bench
 * @Date 2024/06/24 14:19
 **/

@Getter
@AllArgsConstructor
public enum InfraExceptionEnum {
    DATABASE_CONNECTION_ERROR(1001, "Database connection error"),
    WAIN_ID_NOT_EXISTS(1002, "WarnId not found"),
    REDIS_CONNECTION_ERROR(1003, "Redis error");

    private final int code;
    private final String message;
}
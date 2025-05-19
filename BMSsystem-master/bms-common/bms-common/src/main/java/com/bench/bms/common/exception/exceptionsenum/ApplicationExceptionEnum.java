package com.bench.bms.common.exception.exceptionsenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author bench
 * @Date 2024/06/24 14:22
 **/


@Getter
@AllArgsConstructor
public enum ApplicationExceptionEnum {
    INVALID_INPUT(2001, "Invalid input"),
    USER_NOT_FOUND(2002, "User not found"),
    DUPLICATE_ENTRY(2003, "Duplicate entry");

    private final int code;
    private final String message;
}
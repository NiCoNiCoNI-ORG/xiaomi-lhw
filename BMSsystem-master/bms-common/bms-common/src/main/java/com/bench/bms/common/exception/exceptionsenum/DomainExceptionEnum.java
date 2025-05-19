package com.bench.bms.common.exception.exceptionsenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author bench
 * @Date 2024/06/24 14:30
 **/

@Getter
@AllArgsConstructor
public enum DomainExceptionEnum {
//    REDIS_CONNECTION_ERROR(2001, "Invalid input"),
    RULE_NOT_FOUND(2002, "Rule not found!"),
    CAR_NOT_FOUND(2003, "Car not found!");

    private final int code;
    private final String message;
}

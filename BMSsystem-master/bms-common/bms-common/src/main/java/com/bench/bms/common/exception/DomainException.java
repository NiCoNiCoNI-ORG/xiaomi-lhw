package com.bench.bms.common.exception;

import com.bench.bms.common.exception.exceptionsenum.ApplicationExceptionEnum;
import com.bench.bms.common.exception.exceptionsenum.DomainExceptionEnum;
import lombok.Getter;

/**
 * @Author bench
 * @Date 2024/06/24 14:44
 **/
@Getter
public class DomainException extends RuntimeException{

    private final int code;
    private final String message;

    public DomainException(DomainExceptionEnum domainExceptionEnum){
        super(domainExceptionEnum.getMessage());
        this.code = domainExceptionEnum.getCode();
        this.message = domainExceptionEnum.getMessage();
    }
}

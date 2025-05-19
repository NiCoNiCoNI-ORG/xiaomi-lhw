package com.bench.bms.common.exception;

import com.bench.bms.common.exception.exceptionsenum.ApplicationExceptionEnum;
import lombok.Getter;

/**
 * @Author bench
 * @Date 2024/06/24 14:44
 **/

@Getter
public class ApplicationException extends RuntimeException{

    private final int code;
    private final String message;

    public ApplicationException(ApplicationExceptionEnum applicationExceptionEnum){
        super(applicationExceptionEnum.getMessage());
        this.code = applicationExceptionEnum.getCode();
        this.message = applicationExceptionEnum.getMessage();
    }

}

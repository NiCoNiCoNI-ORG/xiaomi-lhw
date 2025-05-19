package com.bench.bms.common.exception;

import com.bench.bms.common.model.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author bench
 * @Date 2024/06/16 14:21
 **/

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseRes<Void> unexpectedException(Exception e){
        log.error("request error", e);
        return BaseRes.error("系统异常");
    }


    @ExceptionHandler(value =BindException.class)
    @ResponseBody
    public BaseRes<Void> bindException(BindException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String error = fieldErrors.stream()
                .map(o -> o.getDefaultMessage())
                .collect(Collectors.joining(","));
        return BaseRes.error(error);
    }

    //捕捉参数校验异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseRes<Void> methodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        String error = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));
        return BaseRes.requestError(error);
    }

}

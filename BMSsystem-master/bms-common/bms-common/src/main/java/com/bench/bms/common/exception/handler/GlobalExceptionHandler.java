package com.bench.bms.common.exception.handler;

import com.bench.bms.common.exception.ApplicationException;
import com.bench.bms.common.exception.DomainException;
import com.bench.bms.common.exception.InfraException;
import com.bench.bms.common.model.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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

    // 捕获数据库异常
    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public BaseRes<Void> mySqlException(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return BaseRes.error("该数据有误，操作失败");
        }
        return BaseRes.error("数据异常，操作失败！");
    }

    @ResponseBody
    @ExceptionHandler(InfraException.class)
    public BaseRes<Void> infraException(InfraException e){
        log.error("Infra error", e);
        return BaseRes.requestError(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(DomainException.class)
    public BaseRes<Void> domainException(DomainException e){
        log.error("Domain error", e);
        return BaseRes.requestError(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ApplicationException.class)
    public BaseRes<Void> applicationException(ApplicationException e){
        log.error("Application error", e);
        return BaseRes.requestError(e.getMessage());
    }
}

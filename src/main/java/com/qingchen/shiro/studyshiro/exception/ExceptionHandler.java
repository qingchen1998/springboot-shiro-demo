package com.qingchen.shiro.studyshiro.exception;

import com.qingchen.shiro.studyshiro.enums.StatusEnums;
import com.qingchen.shiro.studyshiro.vo.ResponseCode;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * 统一捕捉异常，统一处理（PS：也可以手动抛出异常，再定义其他处理逻辑，不会被ExceptionHandler捕捉）
 * @author Created by QingChen on 2019/11/14 10:09
 */
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = AuthorizationException.class)
    public ResponseCode globalException(Exception e){
        if(e instanceof AuthorizationException){
            return ResponseCode.error(StatusEnums.PERMISSION_ERROR);
        }else{
            System.out.println(e);
            return ResponseCode.error(StatusEnums.OTHER);
        }
    }

}

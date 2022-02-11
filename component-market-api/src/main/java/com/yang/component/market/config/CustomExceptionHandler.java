package com.yang.component.market.config;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.component.market.manager.dto.Result;


@ControllerAdvice
public class CustomExceptionHandler {

    // 捕获方法参数校验异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<String> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> message = e.getConstraintViolations();
        HashMap<String, Object> map = new HashMap<>();
        message.forEach(msg -> {
            String path = msg.getPropertyPath().toString();
            String field = path.substring(path.indexOf(".") + 1);
            map.put(field, msg.getMessageTemplate());
        });
        return Result.error(map);
    }

    // 捕获实体参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String> resolveMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        HashMap<String, Object> map = new HashMap<>();
        allErrors.forEach(error -> {
            FieldError fieldError = (FieldError) error;
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return Result.error(map);
    }
}

package com.cwr.exception;

import com.cwr.pojo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${debug-mode}")
    private boolean debugMode;


    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> exception(Exception e) {
        if (debugMode) {
            e.printStackTrace();
        } else {
            log.error(e.getMessage());
        }
        return ApiResponse.error("对不起，请联系管理员");
    }
}

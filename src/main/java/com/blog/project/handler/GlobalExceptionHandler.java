package com.blog.project.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice        // 모든 exception 발생할 시, 이 class 로 들어옴
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>"+e.getMessage()+"</h1>";
    }

//    // 모든 exception 을 받을 수 있는 handler
//    @ExceptionHandler(value=Exception.class)
//    public String handleException(Exception e) {
//        return "<h1>"+e.getMessage()+"</h1>";
//    }
}

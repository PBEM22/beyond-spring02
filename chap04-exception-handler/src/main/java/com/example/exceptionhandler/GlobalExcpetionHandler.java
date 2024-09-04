package com.example.exceptionhandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* @ControllerAdvice : 해당 어노테이션이 적용 된 클래스의 @EXceptionHalder는 전역적으로 가능하다. */
@RestControllerAdvice
public class GlobalExcpetionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException e){
        System.out.println("message : " + e.getMessage());

        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String memberRegistExceptionHandler(MemberRegistException e, Model model){

        model.addAttribute("exception", e);

        return "error/memberRegist";
    }

    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(Exception e){
        return "error/default";
    }
}

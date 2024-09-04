package com.example.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("/controller-null")
    public String nullPointerExceptionTest(){
        String str = null;
        System.out.println(str.charAt(0));  // 의도적인 NullPointerException 발생
        return "/";
    }

    @GetMapping("/controller-user")
    public String userExceptionTest() throws MemberRegistException {
        boolean check = true;
        if (check) throw new MemberRegistException("당신 같은 사람은 회원으로 받을 수 없습니다.");

        return "/";
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException e){
        System.out.println("message : " + e.getMessage());
        System.out.println("특정 Controller 범위의 Exception Handler 동작");
        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String memberRegistExceptionHandler(MemberRegistException e, Model model){

        System.out.println("특정 Controller 범위의 Exception Handler 동작");


        model.addAttribute("exception", e);

        return "error/memberRegist";
    }
}

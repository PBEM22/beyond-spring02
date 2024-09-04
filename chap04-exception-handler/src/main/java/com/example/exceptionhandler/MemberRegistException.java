package com.example.exceptionhandler;

/* 사용자 정의 Exception 클래스 */
public class MemberRegistException extends Exception{

    public MemberRegistException(String message) {
        super(message);
    }
}

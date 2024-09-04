package com.example.handlermethod;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* 커맨드 객체로 사용하기 위해서는 name 속성 값과 필드명이 일치하도록 작성해야 한다. */
@Getter @Setter
@ToString
public class MenuDTO {
    private String name;
    private int price;
    private int categoryCode;
    private String orderableStatus;

    public MenuDTO() {
    }
}

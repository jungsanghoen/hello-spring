package com.example.hello.hellospring.controller;

//POST 요청 시 스프링이 name에 setName 하여 전달하게 됨.
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

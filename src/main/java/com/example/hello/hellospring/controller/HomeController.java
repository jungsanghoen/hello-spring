package com.example.hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//index.html static이 있으므로 아무것도 없으면 원래
//index.html을 바라보나, 우선순위가 있어서 Get Mapping 시 컨트롤러를 따라감
@Controller
public class HomeController {

    @GetMapping("/")
    public String home()
    {
        return "home";
    }

}

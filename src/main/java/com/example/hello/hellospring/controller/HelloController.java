package com.example.hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello-----aa");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name="name", required = false) String name,Model model)
    {
        if(name == null) {
            name = "Null 값이에용";
        }

        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //ResponseBody -> http 헤더부, 바디부 중에 바디부에 직접 넣어주겠다는 부분
    public String helloString(@RequestParam("name") String name)
    {
        return "Hello Response  " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name)
    {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;
        private String name2;

        public String getName() {
            return name;
        }
        /*
        public String getName2() {
            return name2;
        }
        */
        public void setName(String name) {
            this.name = name;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }
    }
}

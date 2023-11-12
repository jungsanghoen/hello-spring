package com.example.hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

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

    @GetMapping("cookie/hello-cookie")
    @ResponseBody
    public Hello helloAndCookie(@RequestParam("name") String name, HttpServletResponse response)
    {
        Hello hello = new Hello();
        hello.setName(name);

        Cookie myCookie = new Cookie("myCookie", "thisismyCookie");
        myCookie.setMaxAge(600);//10분

        response.addCookie(myCookie);
        return hello;
    }

    @GetMapping("cookie/get-cookie")
    @ResponseBody
    public myCookie getCookie(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();

        if(cookies != null)
        {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("myCookie")) {
                    myCookie myCookie = new myCookie();
                    myCookie.setCookieValue("bingo");
                    return myCookie;
                }
            }
        }
        myCookie myCookie = new myCookie();
        myCookie.setCookieValue("none_first!");
        return myCookie;
    }


    static class myCookie{
        private String cookieValue;

        public void setCookieValue(String cookieValue) {
            this.cookieValue = cookieValue;
        }

        public String getCookieValue() {
            return cookieValue;
        }
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

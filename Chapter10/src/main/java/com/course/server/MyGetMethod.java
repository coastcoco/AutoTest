package com.course.server;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@SpringBootApplication
@RestController
public class MyGetMethod {

    //实现mock中会返回cookies信息的get请求--startupWithCookies.json中第一个mock接口

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response) {

        //实现获取cookies涉及到2个方法
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse 装响应信息的类

        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你获取cookies信息成功!!!";
    }


    //要求客户端携带cookies访问的接口
    @RequestMapping(value = "/getwithcookies", method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();          //多个cookis可以整个数组存放

        //一般判断一个对象为空的小技巧,如下。否则写成if(cookis == null)会有风险
        if (Objects.isNull(cookies)) {
            return "你必须携带cookies信息来";
        }

        //验证Cookies
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return  "恭喜你访问成功！！！";
            }
        }

        return "呵呵，你必须携带cookies信息来";
    }
}


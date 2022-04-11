package com.eatery.cd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeadController {
    //从主页跳转到login.jsp页面
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }
    //从主页跳转到residter.jsp页面
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "/register";
    }
    //从登陆页面跳转到residter.jsp页面
    @RequestMapping("/loginToRegister")
    public String loginToRegister(){
        return "/register";
    }
    //从注册页面跳转到登陆页面
    @RequestMapping("/registerToLogin")
    public  String registerToLogin(){
        return  "/login";
    }
    //跳转到user_center.jsp页面
    @RequestMapping("/toUser_center")
    public String toUser_center(){
        return "/pages/user/user_center";
    }
    //跳转到订单页面
    @RequestMapping("/toUser_orderlist")
    public String toUser_orderlist(){
        return "/pages/user/user_orderlist";
    }
}








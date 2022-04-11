package com.eatery.cd.controller;

import com.eatery.cd.pojo.Product;
import com.eatery.cd.pojo.User;
import com.eatery.cd.service.UserService;
import com.eatery.cd.tools.VerifyCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    //登陆
    @RequestMapping("/loginLogin")
    public String loginLogin(Model model, String username, String password, HttpSession userSession) {

//        User user = userService.findUserByU_P(username, password);
//        userSession.setAttribute("user", null);
//        if (user != null) {
//            model.addAttribute("user", user);
//            userSession.setAttribute("user", user);
//            Map<Product,Integer> cartMap = new HashMap<>();
//            userSession.setAttribute("cart",cartMap);
//            return "redirect:/proHome";
//        }

        // 判断用户名和密码是否为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            // 用户名或者密码为空
            model.addAttribute("errorInfo", "用户名或者密码为空");

            return "/login";
        }

        //通过subject进行登录操作
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            subject.getSession().setAttribute("user", user);

            Map<Product, Integer> cartMap = new HashMap<>();
            subject.getSession().setAttribute("cart", cartMap);

            List<String> userRole = userService.findAdminRole(user.getUserId());
            subject.getSession().setAttribute("userRole", userRole);

            return "redirect:/proHome";
        } catch (AuthenticationException e) {
            e.printStackTrace(); //打印异常错误
            // 用户名或者密码为空
            model.addAttribute("errorInfo", "用户名或者密码不正确");

            return "/login";
        }
        // model.addAttribute("errorInfo", "用户名或密码错误");

        //return "/login";
    }


    //注册前先查询用户是否存在
    @RequestMapping("registerQuery")
    public void registerQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        User user = userService.finuserByUsername(username);

        if (user != null) {
            response.getWriter().write("123");
        }

    }

    //注册用户
    @RequestMapping("/registerUser")
    public String registerUser(User user) {
        User u = userService.finuserByUsername(user.getUsername());
        if (u != null) {
            return "/register";
        }
        userService.registerUser(user);
        userService.registerRole(user.getUserId());
        System.out.println("=========================================================================================");
        return "/login";
    }


    /*退出登录*/
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session) {
        //通过subject进行登录操作
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("user", "");
        return "redirect:/proHome";
    }


    //获取验证码
    @RequestMapping(value = "getVCode", method = RequestMethod.GET)
    public void getVCode(HttpServletResponse response, HttpServletRequest request) {

       /* response.setDateHeader("Expires", -1);
        response.setHeader("Cache-Control", "no-cache");*/

        VerifyCode vc = new VerifyCode();

        try {
            vc.drawImage(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String code = vc.getCode();
        request.getSession().setAttribute("code", code);


    }


    @RequestMapping("inputCode")
    public void inputVcode(HttpServletRequest request, HttpServletResponse response) throws IOException {


        //JSONObject jsonObject = new JSONObject();
        String code = (String) request.getSession().getAttribute("code");
        String inputCode = request.getParameter("inputCode");

        System.out.println(code + "====================");
        System.out.println(inputCode + "====================");

        if (inputCode != null) {
            if (!inputCode.equals(code.toLowerCase())) {
                response.getWriter().write("456");
                System.out.println("不等");
            }

        } else {
            response.getWriter().write("nonono");
            System.out.println("nononononononon");
            System.out.println("为空");
        }

    }


}
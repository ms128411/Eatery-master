package net.togogo.controller;

import net.togogo.domain.User;
import net.togogo.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class LoginController {

    @Resource
    UserServiceImpl userService;

    /*登出*/
    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request)
    {
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        return "redirect:login";
    }

    /*验证登录*/
    @RequestMapping("/login")
    public String login(User user, Model model, HttpServletRequest request)
    {
        /*调用验证登录的逻辑类去验证登录*/
        /*登录类型为管理员，跳转到后台页面*/
        if(userService.checkLogin(user.getUserName(),user.getPassword(),user.getUserType(),request)==true)
        {
            if(user.getUserType()==1){//如果是管理员则跳转到后台
                return "index";
            }
            else {//如果是游客则跳转到主页，主页地址待定
                return "welcome";
            }
        }
        else {
            model.addAttribute("msg","用户名或者密码或用户类型错误！");
            return "login";
        }



    }

    /*访问欢迎页面*/
    @RequestMapping("/welcome")
    public String welcome()
    {
        return "welcome";
    }
}

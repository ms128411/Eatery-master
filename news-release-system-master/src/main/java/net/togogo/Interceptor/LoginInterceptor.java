package net.togogo.Interceptor;

import net.togogo.domain.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*自定义一个拦截器，验证用户是否登录*/
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入登录拦截器----------------------------");
        /*拦截未登录的用户，如果用户未登录，不允许访问*/
        /*1.从session内获取用户信息，如果用户信息不为空，说明已经登录*/
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null) {
            /*若没有登录，返回登录页面*/
            response.sendRedirect("login");
            return false;
        } else {
            System.out.println("登录用户为:" + user.getUserName());
            /*返回true允许访问*/
            return true;
        }
    }
}
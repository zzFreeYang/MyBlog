package com.springboot.demo.interceptor;

import com.springboot.demo.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BackInterceptor implements HandlerInterceptor {
    private static String username = "qianyix";
    private static String password = "123456";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;
        User user = (User) request.getSession().getAttribute("user");
        if(null == user) {
            flag = false;
        }else {
            //对账户进行验证，是否正确
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                flag = true;
            }
            else {
                flag = false;
                // todo  否则拦截并跳转到登录
                 response.sendRedirect("/admin/login");
            }
        }
        return flag;
    }
}

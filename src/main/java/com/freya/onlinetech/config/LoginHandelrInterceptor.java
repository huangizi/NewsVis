package com.freya.onlinetech.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandelrInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登陆成功后，应该有用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null)
        {
            request.setAttribute("msg","没有权限，请先登陆");
            request.getRequestDispatcher("/index_1.html").forward(request,response);
            return false;
        }else{
            return true;
        }

    }
}

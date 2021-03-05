package com.Jaziel.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 王杰
 * @time 2020/10/16 14:33
 */
public class MyInterceptor02 implements HandlerInterceptor {
    /**在目标方法执行之前 执行*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle22222...");
        String param = request.getParameter("param");
        /** 是对 true 和 false 的简化，true就可以运行，否则不可以*/
        return true;
    }
    /**在目标方法执行之后，视图对象返回之前执行*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("name", "wj");
        System.out.println("postHandle22222...");
    }
    /**在所有流程执行之后 执行*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion2222...");
    }
}

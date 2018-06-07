package com.itheima.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 其他封装其他Servlet doPost,doGet方法的父类
 */
@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        try {
            //取出方法名称
            String type = request.getParameter("method");
           //调用该方法名的方法
            Method method = this.getClass().getDeclaredMethod(type, HttpServletRequest.class, HttpServletResponse.class);
            //开启访问权限,实现该方法
            method.setAccessible(true);
            method.invoke(this,request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
//        System.out.println(request.getContextPath()+"-----hello UserLoginFilter");
        Object password = request.getSession().getAttribute("password");
        if (password != null) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

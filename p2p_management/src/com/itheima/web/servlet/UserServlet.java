package com.itheima.web.servlet;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {


    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("password");
            response.sendRedirect("/p2p_management/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        System.out.println(getClass().getSimpleName() + username +"---"+ password);
        UserService userService = new UserServiceImpl();
        try {
            if (username == null || "".equals(username.trim())) {
                request.setAttribute("lmsg", "用户名不能为空!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            } else if (password == null || "".equals(password.trim())) {
                request.setAttribute("lmsg", "密码不能为空!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            //如果不为空,调用userService的login方法
            User user = userService.login(username, password);
//            System.out.println(getClass().getSimpleName() + user);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                response.sendRedirect("/p2p_management/views/home.jsp");
                return;
            } else {
                request.setAttribute("lmsg", "用户名密码错误!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.itheima.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Customer;
import com.itheima.domain.JsonResult;
import com.itheima.service.CustomerService;
import com.itheima.service.impl.CustomerServiceImpl;
import com.itheima.utils.Md5Utils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String type = request.getParameter("type");
            switch (type) {
                case "login":
                    login(request, response);
                    break;
                case "notNull":
                    notNull(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notNull(HttpServletRequest request, HttpServletResponse response) {
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String checkCode = request.getParameter("checkCode");
        String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
        String c_nameOrEmail = request.getParameter("c_name");
        String pw = request.getParameter("password");
        //对密码使用Md5Utils编码
        String password = Md5Utils.md5(pw);
        System.out.println(getClass().getSimpleName()+"-comein-"+c_nameOrEmail+password);
        JsonResult jsonResult = new JsonResult();
        if (StringUtils.isBlank(checkCode)) {
            //验证码为空
            jsonResult.setType(0);
            jsonResult.setErrorMsg("验证码未填写");
            String jsonString = JSONObject.toJSONString(jsonResult);
            response.getWriter().write(jsonString);
            return;
        }
        if (StringUtils.isBlank(c_nameOrEmail)) {
            //用户名为空
            jsonResult.setType(0);
            jsonResult.setErrorMsg("用户名未填写");
            String jsonString = JSONObject.toJSONString(jsonResult);
            response.getWriter().write(jsonString);
            return;
        }
        if (StringUtils.isBlank(password)) {
            //密码为空
            jsonResult.setType(0);
            jsonResult.setErrorMsg("密码未填写");
            String jsonString = JSONObject.toJSONString(jsonResult);
            response.getWriter().write(jsonString);
            return;
        }
        if (!checkcode_session.equalsIgnoreCase(checkCode)) {
            //验证码不一致
            jsonResult.setType(0);
            jsonResult.setErrorMsg("验证码不正确");
            String jsonString = JSONObject.toJSONString(jsonResult);
            response.getWriter().write(jsonString);
            return;
        } else {

            CustomerService customerService = new CustomerServiceImpl();
            Customer customer = customerService.login(c_nameOrEmail, password);
            if (customer == null) {
                jsonResult.setType(0);
                jsonResult.setErrorMsg("用户名或密码不正确");

            } else {
                jsonResult.setType(1);
                jsonResult.setErrorMsg("登录成功");
                System.out.println(getClass().getSimpleName() + "--success--" + customer);
                //将账户存入当前域中
                request.getSession().setAttribute("customer", customer);
//                response.sendRedirect("/p2p_home/home.html");
            }
            String jsonString = JSONObject.toJSONString(jsonResult);
            response.getWriter().write(jsonString);
            return;
        }

    }
}

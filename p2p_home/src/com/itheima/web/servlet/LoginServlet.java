package com.itheima.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Customer;
import com.itheima.domain.JsonResult;
import com.itheima.service.CustomerService;
import com.itheima.service.impl.CustomerServiceImpl;
import com.itheima.utils.Md5Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends BaseServlet {


    private void changeStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String email = request.getParameter("email");
        CustomerService customerService = new CustomerServiceImpl();
        int row = customerService.changeStatus(email);
        JsonResult jsonResult = new JsonResult();
        if (row == 0) {
            //返回错误信息,
            jsonResult.setType(0);
            jsonResult.setErrorMsg("您没登陆,请先登录!");

        } else {
            jsonResult.setType(1);
            //更新域中存储的customer对象
            Customer customer = customerService.findByNameOrEmail("", email);
            request.getSession().setAttribute("customer",customer);
        }
        String jsonString = JSONObject.toJSONString(jsonResult);
        response.getWriter().write(jsonString);
    }

    private static Customer getCustomer(HttpServletRequest request) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        return customer;
    }

    //确认访问用户当前是否登录
    private void findCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //首先取出session域中是否存在customer,验证是否登录
        Customer customer = getCustomer(request);
        JsonResult jsonResult = new JsonResult();
        if (customer == null) {
            //返回错误信息,
            jsonResult.setType(0);
            jsonResult.setErrorMsg("您没登陆,请先登录!");

        } else {
            jsonResult.setType(1);
            jsonResult.setContent(customer);
        }
        String jsonString = JSONObject.toJSONString(jsonResult);
        response.getWriter().write(jsonString);
    }

    /**
     * 邮箱验证码发送及回传
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws MessagingException
     */
    private void sendEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonResult jsonResult = new JsonResult();

        //String email = customer.getEmail();
        //生成四位验证码
        String numeric = RandomStringUtils.randomNumeric(6);
        jsonResult.setType(1);
        jsonResult.setContent(numeric);
        String emailMsg = "您好,您的验证码为:" + numeric + ",请尽快完成验证操作.";
        //发送邮箱
        //MailUtils.sendMail(email,emailMsg);
        System.out.println(getClass().getSimpleName() + emailMsg);
        //回传
        String jsonString = JSONObject.toJSONString(jsonResult);
        response.getWriter().write(jsonString);
    }

    /**
     * 用户登录操作
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String checkCode = request.getParameter("checkCode");
        String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
        String c_nameOrEmail = request.getParameter("c_name");
        String pw = request.getParameter("password");
        //对密码使用Md5Utils编码
        String password = Md5Utils.md5(pw);
        System.out.println(getClass().getSimpleName() + "-comein-" + c_nameOrEmail + password);
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
                //将账户存入当前session域中
                request.getSession().setAttribute("customer", customer);
//                response.sendRedirect("/p2p_home/home.html");
            }
            String jsonString = JSONObject.toJSONString(jsonResult);
            response.getWriter().write(jsonString);
        }

    }
}

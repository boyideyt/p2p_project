package com.itheima.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Account;
import com.itheima.domain.Customer;
import com.itheima.domain.JsonResult;
import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AccountServlet", urlPatterns = "/AccountServlet")
public class AccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String type = request.getParameter("type");
            switch (type) {
                case "showAccount":
                    showAccount(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        JsonResult jsonResult = new JsonResult();
        //去除当前session域中的customer
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        if (customer == null) {
            jsonResult.setType(0);
            return;
        }
        AccountService accountService = new AccountServiceImpl();
        Account account = accountService.findAccount(customer.getId());
        String jsonString = JSONObject.toJSONString(account);
        response.getWriter().write(jsonString);
    }
}

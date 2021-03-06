package com.itheima.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Account;
import com.itheima.domain.Customer;
import com.itheima.domain.JsonResult;
import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AccountServlet", urlPatterns = "/AccountServlet")
public class AccountServlet extends BaseServlet {

    /**
     * space.js调用的方法
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     */
    private void showAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        JsonResult jsonResult = new JsonResult();
        //去除当前session域中的customer
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        System.out.println(getClass().getSimpleName() + "--session--" + customer);
        if (customer == null) {
            jsonResult.setType(0);
            return;
        }
        jsonResult.setType(1);
        AccountService accountService = new AccountServiceImpl();
        Account account = accountService.findAccount(customer.getId());
        System.out.println(getClass().getSimpleName() + "--result--" + account);
        jsonResult.setContent(account);
        String jsonString = JSONObject.toJSONString(jsonResult);
        response.getWriter().write(jsonString);
    }
}

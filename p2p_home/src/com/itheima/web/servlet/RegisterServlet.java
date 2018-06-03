package com.itheima.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.itheima.service.impl.CustomerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getParameter("method");
        try {
            switch (method) {
                case "reg":
                    reg(request, response);
                    break;
                case "notUsed":
                    notUsed(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void notUsed(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String colName = request.getParameter("colName");
        String checkedValue = request.getParameter("checkedValue");
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(colName+"---"+checkedValue);
        CustomerService customerService = new CustomerServiceImpl();
        Customer customer = customerService.findByNameOrEmail(colName,checkedValue);
        System.out.println(customer);
        String toJSONString = JSONObject.toJSONString(customer);
        response.getWriter().write(toJSONString);
    }

    private void reg(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Customer customer = new Customer();
        BeanUtils.populate(customer, map);
        CustomerService customerService = new CustomerServiceImpl();
        String regmsg = customerService.reg(customer);
        response.getWriter().write(regmsg);
    }
}

package com.itheima.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Customer;
import com.itheima.domain.JsonResult;
import com.itheima.service.CustomerService;
import com.itheima.service.impl.CustomerServiceImpl;
import com.itheima.utils.Md5Utils;
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

    /**
     * 如果返回是null说明该用户名或密码没有占用
     * @param request
     * @param response
     * @throws IOException
     * @throws SQLException
     */
    private void notUsed(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String colName = request.getParameter("c_name");
        String checkedValue = request.getParameter("email");
        CustomerService customerService = new CustomerServiceImpl();
        Customer customer = customerService.findByNameOrEmail(colName,checkedValue);
        JsonResult jsonResult = new JsonResult();
        if(customer==null){
            //没有占用
            jsonResult.setType(1);
        }else{
            //已被占用
            jsonResult.setType(0);
        }
        String jsonString = JSONObject.toJSONString(jsonResult);
        System.out.println(getClass().getSimpleName()+"~~"+jsonString+customer);
        response.getWriter().write(jsonString);
    }

    private void reg(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Customer customer = new Customer();
        BeanUtils.populate(customer, map);
        //使用Md5Utils对密码加密
        String password = customer.getPassword();
        String md5 = Md5Utils.md5(password);
        customer.setPassword(md5);
        System.out.println(getClass().getSimpleName()+"-----"+customer);
        CustomerService customerService = new CustomerServiceImpl();
        String regmsg = customerService.reg(customer);
        response.getWriter().write(regmsg);
    }
}

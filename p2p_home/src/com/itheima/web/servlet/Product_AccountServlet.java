package com.itheima.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Account;
import com.itheima.domain.Customer;
import com.itheima.domain.JsonResult;
import com.itheima.service.AccountService;
import com.itheima.service.P_AService;
import com.itheima.service.impl.AccountServiceImpl;
import com.itheima.service.impl.P_AServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "Product_AccountServlet", urlPatterns = "/Product_AccountServlet")
public class Product_AccountServlet extends BaseServlet {


    private void showAll(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        //检验是否登陆(get customer)
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        JsonResult jsonResult = new JsonResult();
        if (customer == null) {
            //返回错误信息,
            jsonResult.setType(0);
            jsonResult.setErrorMsg("您没登陆,请先登录!");
        } else {
            P_AService p_aService = new P_AServiceImpl();
            Map<String,Object> map = p_aService.showAll(customer.getId());
            System.out.println(getClass().getSimpleName()+"===="+map);
            jsonResult.setType(1);
            jsonResult.setContent(map);
        }
        response.getWriter().write(JSONObject.toJSONString(jsonResult));
    }

    private void buy(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        //获取数据
        int pid = Integer.parseInt(request.getParameter("pid"));
        Double money = Double.valueOf(request.getParameter("money"));
        System.out.println(pid + "----------" + money);
        //检验是否登陆(get customer)
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        JsonResult jsonResult = new JsonResult();
        if (customer == null) {
            //返回错误信息,
            jsonResult.setType(0);
            jsonResult.setErrorMsg("您没登陆,请先登录!");
        } else {
            //检验余额是否大于money
            AccountService accountService = new AccountServiceImpl();
            Account account = accountService.findAccount(customer.getId());
            System.out.println(getClass().getSimpleName() + "--result--" + account);

            if (account.getBalance() < money) {
                //余额不足
                jsonResult.setType(0);
                jsonResult.setErrorMsg("余额不足,请先充钱!");
            }else{
                P_AService p_aService = new P_AServiceImpl();
                int row = p_aService.buy(account,customer,money,pid);
                if(row>0){
                    jsonResult.setType(1);
                }else {
                    jsonResult.setType(0);
                    jsonResult.setErrorMsg("交易失败,请等下重试.");
                }
            }

        }
        String jsonString = JSONObject.toJSONString(jsonResult);
        response.getWriter().write(jsonString);
    }
}

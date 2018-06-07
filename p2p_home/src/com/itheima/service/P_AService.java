package com.itheima.service;

import com.itheima.domain.Account;
import com.itheima.domain.Customer;

import java.sql.SQLException;
import java.util.Map;

public interface P_AService {
    int buy(Account account, Customer customer, Double money,int pid) throws SQLException;

    Map<String,Object> showAll(int id) throws SQLException;
}

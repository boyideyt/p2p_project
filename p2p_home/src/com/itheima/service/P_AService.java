package com.itheima.service;

import com.itheima.domain.Account;
import com.itheima.domain.Customer;

import java.sql.SQLException;

public interface P_AService {
    int buy(Account account, Customer customer, Double money,int pid) throws SQLException;
}

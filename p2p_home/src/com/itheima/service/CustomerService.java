package com.itheima.service;

import com.itheima.domain.Customer;

import java.sql.SQLException;

public interface CustomerService {
    String reg(Customer customer) throws SQLException;

    Customer findByNameOrEmail(String c_name, String email) throws SQLException;

    Customer login(String c_nameOrEmail, String password) throws SQLException;

    int changeStatus(String email) throws SQLException;
}

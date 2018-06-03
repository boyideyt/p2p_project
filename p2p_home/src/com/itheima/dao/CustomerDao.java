package com.itheima.dao;

import com.itheima.domain.Customer;

import java.sql.SQLException;

public interface CustomerDao {
    int reg(Customer customer) throws SQLException;

    Customer findByNameOrEmail(String colName, String checkedValue) throws SQLException;

    Customer login(String c_nameOrEmail, String password) throws SQLException;
}

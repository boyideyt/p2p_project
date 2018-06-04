package com.itheima.dao;

import com.itheima.domain.Product_Account;

import java.sql.SQLException;

public interface P_ADao {

    int buy(Product_Account product_account) throws SQLException;
}

package com.itheima.dao;

import com.itheima.domain.Product_Account;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface P_ADao {

    int buy(Product_Account product_account) throws SQLException;

    Long count(int id) throws SQLException;

    List<Map<String, Object>> showAll(int id) throws SQLException;

    int updateFlag(String pa_num) throws SQLException;
}

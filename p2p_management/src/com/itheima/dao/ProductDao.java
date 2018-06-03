package com.itheima.dao;

import com.itheima.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> findAll() throws SQLException;

    int addPro(Product product) throws SQLException;

    int editPro(Product product) throws SQLException;

    int delPro(int id) throws SQLException;

    Product findPro(int id) throws SQLException;
}

package com.itheima.service;

import com.itheima.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findAll() throws SQLException;

    int editPro(Product product) throws SQLException;

    int addPro(Product product) throws SQLException;

    int delPro(int id) throws SQLException;

    Product findPro(int id) throws SQLException;
}

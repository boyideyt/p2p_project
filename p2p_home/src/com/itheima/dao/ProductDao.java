package com.itheima.dao;

import com.itheima.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
     Product findPro(int id) throws SQLException;
}

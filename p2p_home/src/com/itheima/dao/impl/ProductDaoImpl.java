package com.itheima.dao.impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class ProductDaoImpl implements ProductDao {

    @Override
    public Product findPro(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Product product = queryRunner.query("select * from product where id = ?", new BeanHandler<Product>(Product.class), id);
        return product;
    }
}

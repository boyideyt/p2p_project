package com.itheima.dao.impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.utils.DuridJDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private QueryRunner queryRunner = new QueryRunner(DuridJDBCUtils.getDataSource());

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = queryRunner.query("select * from product;", new BeanListHandler<Product>(Product.class));
        return productList;
    }

    @Override
    public int addPro(Product product) throws SQLException {
        int update = queryRunner.update("INSERT INTO product VALUES (NULL,?,?,?,?,NULL)", product.getProNum(), product.getProName(), product.getProLimit(), product.getAnnualized());
        return update;
    }

    @Override
    public int editPro(Product product) throws SQLException {
        int update = queryRunner.update("UPDATE product set proNum =?,proName =?,proLimit=?,annualized = ? where id = ?;", product.getProNum(), product.getProName(), product.getProLimit(), product.getAnnualized(),product.getId());
        return update;
    }

    @Override
    public int delPro(int id) throws SQLException {
        int update = queryRunner.update("delete from product where id = ?;", id);
        return update;
    }

    @Override
    public Product findPro(int id) throws SQLException {
        Product product = queryRunner.query("select * from product where id = ?", new BeanHandler<Product>(Product.class), id);
        return product;
    }
}

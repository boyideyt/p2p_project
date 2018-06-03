package com.itheima.service.impl;

import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.ProductDaoImpl;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    /**
     * 搜索所有商品
     */

    @Override
    public List<Product> findAll() throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.findAll();
        return list;
    }

    @Override
    public int editPro(Product product) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        int row =productDao.editPro(product);
        return row;
    }

    @Override
    public int addPro(Product product) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        int row = productDao.addPro(product);
        return row;
    }

    @Override
    public int delPro(int id) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        int row = productDao.delPro(id);
        return row;
    }

    @Override
    public Product findPro(int id) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        Product product= productDao.findPro(id);
        return product;
    }
}

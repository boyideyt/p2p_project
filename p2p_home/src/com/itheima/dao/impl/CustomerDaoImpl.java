package com.itheima.dao.impl;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {


    @Override
    public int reg(Customer customer) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        int update = queryRunner.update(JDBCUtils.getConnection(),"insert into customer values(null,?,?,0,?)", customer.getC_name(), customer.getEmail(), customer.getPassword());
        return update;
    }

    @Override
    public Customer findByNameOrEmail(String colName, String checkedValue) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from customer where c_name = ? or email = ?" ;
        System.out.println(getClass().getSimpleName()+"===="+sql);
        Customer customer = queryRunner.query(sql, new BeanHandler<Customer>(Customer.class), colName,checkedValue);
        return customer;
    }

    @Override
    public Customer login(String c_nameOrEmail, String password) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from customer where (c_name = ? or email = ?) and password = ?" ;
        System.out.println(getClass().getSimpleName()+"===="+sql+"----"+c_nameOrEmail+password);
        Customer customer = queryRunner.query(sql, new BeanHandler<Customer>(Customer.class), c_nameOrEmail,c_nameOrEmail,password);
        return customer;
    }
}

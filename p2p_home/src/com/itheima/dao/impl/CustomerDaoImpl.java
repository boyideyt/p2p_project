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
        String sql = "select * from customer where "+colName+" = ?";
        System.out.println(sql);
        Customer customer = queryRunner.query(sql, new BeanHandler<Customer>(Customer.class), checkedValue);
        return customer;
    }
}

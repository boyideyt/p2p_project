package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Customer;
import com.itheima.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public int reg(String email) throws SQLException {
        Customer customer = queryRunner.query(JDBCUtils.getConnection(), "select * from customer where email = ?", new BeanHandler<Customer>(Customer.class), email);
        int update = queryRunner.update(JDBCUtils.getConnection(), "insert into account values(null,null,null,null,?)", customer.getId());
        return update;
    }
}

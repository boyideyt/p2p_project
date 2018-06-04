package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.domain.Customer;
import com.itheima.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {


    @Override
    public int reg(String email) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Customer customer = queryRunner.query(JDBCUtils.getConnection(), "select * from customer where email = ?", new BeanHandler<Customer>(Customer.class), email);
        int update = queryRunner.update(JDBCUtils.getConnection(), "insert into account values(null,null,null,null,?)", customer.getId());
        return update;
    }

    @Override
    public Account findAccount(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Account account = queryRunner.query("select * from account where c_id=?", new BeanHandler<Account>(Account.class), id);
        System.out.println(getClass().getSimpleName()+"----"+account+id);
        return account;
    }

    @Override
    public int buy(Double newBalance, int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        int update = queryRunner.update(JDBCUtils.getConnection(), "update account set balance = ? where id = ?", newBalance,id);
        return update;
    }
}

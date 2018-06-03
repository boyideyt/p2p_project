package com.itheima.dao;

import com.itheima.domain.Account;

import java.sql.SQLException;

public interface AccountDao {
    int reg(String email) throws SQLException;

    Account findAccount(int id) throws SQLException;
}

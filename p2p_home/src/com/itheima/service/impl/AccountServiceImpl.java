package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;

import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {
    @Override
    public Account findAccount(int id) throws SQLException {
        AccountDao accountDao = new AccountDaoImpl();
        Account account = accountDao.findAccount(id);
        return account;
    }
}

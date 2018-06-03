package com.itheima.service;

import com.itheima.domain.Account;

import java.sql.SQLException;

public interface AccountService {
    Account findAccount(int id) throws SQLException;
}

package com.itheima.dao;

import java.sql.SQLException;

public interface AccountDao {
    int reg(String email) throws SQLException;
}

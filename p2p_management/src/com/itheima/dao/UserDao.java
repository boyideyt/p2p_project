package com.itheima.dao;

import com.itheima.domain.User;

import java.sql.SQLException;

public interface UserDao {
    User findUser(String username, String password) throws SQLException, NoSuchMethodException;
}

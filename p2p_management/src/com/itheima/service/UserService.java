package com.itheima.service;

import com.itheima.domain.User;

import java.sql.SQLException;

public interface UserService {
    User login(String username, String password) throws NoSuchMethodException, SQLException;
}

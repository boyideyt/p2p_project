package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public User login(String username, String password) throws NoSuchMethodException, SQLException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findUser(username,password);
        System.out.println(getClass().getSimpleName()+user);
        return user;
    }
}

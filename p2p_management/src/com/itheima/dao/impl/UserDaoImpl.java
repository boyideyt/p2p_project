package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.DuridJDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUser(String username, String password) throws SQLException, NoSuchMethodException {
        QueryRunner queryRunner = new QueryRunner(DuridJDBCUtils.getDataSource());
        System.out.println(getClass().getClass());
        String sql = "select * from user where username=? and password=?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class), username, password);
//        System.out.println(getClass().getSimpleName()+user);
        return user;
    }
}

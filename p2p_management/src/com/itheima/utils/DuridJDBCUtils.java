package com.itheima.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@JdbcAnnotation(url = "jdbc:mysql:///p2p")
public class DuridJDBCUtils {


    private static final DruidDataSource dataSource=new DruidDataSource();
    static{
        Class clazz = DuridJDBCUtils.class;
        JdbcAnnotation jdbcDb =(JdbcAnnotation) clazz.getAnnotation(JdbcAnnotation.class);
        dataSource.setDriverClassName(jdbcDb.driverClassName());
        dataSource.setUrl(jdbcDb.url());
        dataSource.setUsername(jdbcDb.user());
        dataSource.setPassword(jdbcDb.password());
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    public static DataSource getDataSource(){
        return dataSource;
    }
}

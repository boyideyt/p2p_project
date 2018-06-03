package com.itheima.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC的工具类
 * 增加线程控制
 *
 * @author jt
 */
public class JDBCUtils {
    //创建一个当前线程对象// 相当一个Map<Thread,value>
    private static ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<Connection>();
    // 创建一个连接池：但是这个连接池只需要创建一次即可。
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    /**
     * 获得与线程绑定的连接的方法
     *
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = THREAD_LOCAL.get();
        if (conn == null) {
            conn = dataSource.getConnection();
            THREAD_LOCAL.set(conn);
        }
        return conn;
    }

    /**
     * 获得连接池:
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     *  开启事务
     *  autoCommit - true启用自动提交模式； 在这里面我们用false禁用它
     */
    public static void startTransaction() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.setAutoCommit(false); // 设置手动事务
        }
    }

    /**
     * 事务回滚
     */
    public static void rollback() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.rollback();
        }
    }

    /**
     * 事务提交
     */
    public static void commit() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.commit();
        }
    }

    /**
     * 关闭connection
     */
    public static void close() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.close();
        }
    }
}


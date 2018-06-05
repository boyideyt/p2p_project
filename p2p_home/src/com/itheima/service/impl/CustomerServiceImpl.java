package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.CustomerDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.dao.impl.CustomerDaoImpl;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.itheima.utils.JDBCUtils;

import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {

    /**
     * 注册用户及账户
     * @param customer
     * @return
     * @throws SQLException
     */
    @Override
    public String reg(Customer customer) throws SQLException {
        try {
            //开启事务
            JDBCUtils.startTransaction();

            //创建account对象
            CustomerDao customerDao = new CustomerDaoImpl();
            int row = customerDao.reg(customer);

            AccountDao accountDao=new AccountDaoImpl();
            int row2 = accountDao.reg(customer.getEmail());

            StringBuilder sb = new StringBuilder();
            if (row > 0) {
                sb.append("customer ok");
            }
            if (row2 > 0) {
                sb.append("account ok");
            }
            //提交事务
            JDBCUtils.commit();
            return sb.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.rollback();
            JDBCUtils.close();
        }
        return "创建失败";
    }

    /**
     * 检验注册时用户名或密码是否已存在,存在则返回存在的账户
     * @return
     * @throws SQLException
     */
    @Override
    public Customer findByNameOrEmail(String c_name, String email) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl();
        Customer customer = customerDao.findByNameOrEmail(c_name,email);
        return customer;
    }

    @Override
    public Customer login(String c_nameOrEmail, String password) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl();
        Customer customer = customerDao.login(c_nameOrEmail,password);
        return customer;
    }

    @Override
    public int changeStatus(String email) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl();
        int row = customerDao.changeStatus(email);
        return row;
    }
}

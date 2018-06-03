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

    @Override
    public Customer findByNameOrEmail(String colName, String checkedValue) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl();
        Customer customer = customerDao.findByNameOrEmail(colName,checkedValue);
        return customer;
    }
}

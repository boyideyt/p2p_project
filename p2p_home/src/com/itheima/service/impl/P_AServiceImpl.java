package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.P_ADao;
import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.dao.impl.P_ADaoImpl;
import com.itheima.dao.impl.ProductDaoImpl;
import com.itheima.domain.Account;
import com.itheima.domain.Customer;
import com.itheima.domain.Product;
import com.itheima.domain.Product_Account;
import com.itheima.service.P_AService;
import com.itheima.utils.JDBCUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

public class P_AServiceImpl implements P_AService {

    /**
     * 开启事务
     * 1.创建P_A记录
     * 2.Account账户金额变化
     */
    @Override
    public int buy(Account account, Customer customer,Double money, int pid) throws SQLException {
        int result = 0;
        BigDecimal cost = BigDecimal.valueOf(money);
        BigDecimal balance = BigDecimal.valueOf(account.getBalance());
        Double newBalance = balance.subtract(cost).doubleValue();
        try {
            //1.交易开始
            JDBCUtils.startTransaction();
            AccountDao accountDao = new AccountDaoImpl();
            //2.修改账户余额
            int update = accountDao.buy(newBalance, account.getId());
            P_ADao p_aDao = new P_ADaoImpl();
            //3.计算收益值
            //3.1查询商品
            ProductDao productDao = new ProductDaoImpl();
            Product pro = productDao.findPro(pid);
            //3.2获取商品期限
            BigDecimal proLimit = BigDecimal.valueOf(pro.getProLimit());
            //3.3获取商品利率
            BigDecimal annualized = BigDecimal.valueOf(pro.getAnnualized()/1200);
            Double interest = cost.multiply(annualized).multiply(proLimit).doubleValue();
            //4.创建Product_Account对象
            Product_Account product_account = new Product_Account();
            product_account.setPa_num("hm"+new Date().getTime());
            product_account.setC_id(customer.getId());
            product_account.setInterest(interest);
            product_account.setMoney(money);
            product_account.setP_id(pid);
            //验证返回值是否均>1
            int add = p_aDao.buy(product_account);
            if (update > 0 && add > 0) {
                result = 1;
            } else {
                JDBCUtils.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JDBCUtils.rollback();
        } finally {
            JDBCUtils.commit();
            JDBCUtils.close();
        }
        return result;
    }
}

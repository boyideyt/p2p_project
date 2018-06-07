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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P_AServiceImpl implements P_AService {

    /**
     * 开启购买事务
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
//            BigDecimal proLimit = BigDecimal.valueOf(pro.getProLimit());
            BigDecimal proLimit = new BigDecimal(pro.getProLimit());
            //3.3获取商品利率
//            BigDecimal annualized = BigDecimal.valueOf(pro.getAnnualized());
            BigDecimal annualized = new BigDecimal(pro.getAnnualized());
            Double interest = cost.multiply(annualized).multiply(proLimit).divide(new BigDecimal(1200),3,BigDecimal.ROUND_HALF_UP).doubleValue();
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

    /**
     * 统计用户购买记录条数封装到map
     * 再将每条购买记录封装结果封装到map
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> showAll(int id) throws SQLException {
        try{
            JDBCUtils.startTransaction();
            HashMap<String, Object> hashMap = new HashMap<>();
            P_ADao p_aDao = new P_ADaoImpl();
            //添加数量
            int count = Math.toIntExact(p_aDao.count(id));
            hashMap.put("amount",count);
            //添加结果map的集合
            List<Map<String, Object>> mapList = p_aDao.showAll(id);
            System.out.println(mapList);
            hashMap.put("mapList",mapList);
            //添加账户对象
            AccountDao accountDao = new AccountDaoImpl();
            Account account = accountDao.findAccount(id);
            hashMap.put("account",account);

            return hashMap;
        }catch (Exception e){
            e.printStackTrace();
            JDBCUtils.rollback();
        }finally {
            JDBCUtils.commit();
            JDBCUtils.close();
        }
        return null;
    }
}

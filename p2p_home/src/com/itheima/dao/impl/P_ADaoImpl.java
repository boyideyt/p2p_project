package com.itheima.dao.impl;

import com.itheima.dao.P_ADao;
import com.itheima.domain.Product_Account;
import com.itheima.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class P_ADaoImpl implements P_ADao {

    @Override
    public int buy(Product_Account product_account) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        int update = queryRunner.update(JDBCUtils.getConnection(), "INSERT INTO product_account VALUES(NULL,?,NULL,?,?,?,?,null)",
                product_account.getPa_num(), product_account.getC_id(), product_account.getP_id(), product_account.getMoney(), product_account.getInterest());
        return update;
    }

    @Override
    public Long count(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Object[] array = queryRunner.query(JDBCUtils.getConnection(),
                "SELECT COUNT(*) amount FROM product_account WHERE c_id = ?",
                new ArrayHandler(), id);
        Long amount = (Long) array[0];
        return amount;
    }

    @Override
    public List<Map<String, Object>> showAll(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        List<Map<String, Object>> mapList = queryRunner.query(JDBCUtils.getConnection(),
                "SELECT pa.pa_num," +
                        "p.proName," +
                        "p.proNum," +
                        "pa.money," +
                        "p.proLimit," +
                        "pa.interest," +
                        "DATE(pa.pa_date) paDate," +
                        "NOW()>DATE_ADD(pa.pa_date,INTERVAL p.proLimit MONTH) STATU " +
                        "FROM product_account pa,product p WHERE p.id = pa.p_id AND pa.c_id=?;",
                new MapListHandler(),id);
        return mapList;
    }

    /**
     * 线程事务管理
     * @param pa_num
     * @return
     * @throws SQLException
     */
    @Override
    public int updateFlag(String pa_num) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        int update = queryRunner.update(JDBCUtils.getConnection(),"UPDATE product_account set flag = 0 where pa_num=?", pa_num);
        return update;
    }

}

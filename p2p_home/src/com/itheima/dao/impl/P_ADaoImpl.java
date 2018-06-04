package com.itheima.dao.impl;

import com.itheima.dao.P_ADao;
import com.itheima.domain.Product_Account;
import com.itheima.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class P_ADaoImpl implements P_ADao {

    @Override
    public int buy(Product_Account product_account) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        int update = queryRunner.update(JDBCUtils.getConnection(), "INSERT INTO product_account VALUES(NULL,NULL,NULL,?,?,?,?)",
                product_account.getC_id(),product_account.getP_id(),product_account.getMoney(),product_account.getInterest());
        return update;
    }
}

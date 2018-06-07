package com.itheima.dao.impl;

import com.itheima.dao.PeDao;
import com.itheima.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PeDaoImpl implements PeDao {
    @Override
    //搜寻flag为0的所有pa
    public List<Map<String, Object>> findExpiration() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        List<Map<String, Object>> query = queryRunner.query(JDBCUtils.getConnection(),
                "SELECT pa.pa_num,pa.interest,p.proLimit,pa.c_id,pa.flag,NOW()>DATE_ADD(pa.pa_date,INTERVAL p.proLimit MONTH) STATU \n" +
                        " from product_account pa,product p where pa.p_id=p.id and flag=0;", new MapListHandler());
        System.out.println(getClass().getSimpleName()+query);
        return query;
    }
}

package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.P_ADao;
import com.itheima.dao.PeDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.dao.impl.P_ADaoImpl;
import com.itheima.dao.impl.PeDaoImpl;
import com.itheima.service.PeService;
import com.itheima.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PeServiceImpl implements PeService {
    @Override
    public void checkProductExpiration() throws SQLException {
        JDBCUtils.startTransaction();
        try {
            PeDao peDao = new PeDaoImpl();
            //提取所有产品未修改过的产品到期信息,
            List<Map<String, Object>> list = peDao.findExpiration();
            for (Map<String, Object> map : list) {
                //如果statu等于1,则执行修改(此处涉及long类型转换)
                if (1.0 == (Long)map.get("STATU")) {
                    //根据c_id,interest到帐account中的interest
                    Long interest = (Long) map.get("interest");
                    Long c_id = (Long) map.get("c_id");

                    AccountDao accountDao = new AccountDaoImpl();
                    int rowi = accountDao.updateInterest(Math.toIntExact(c_id), interest);

                    //根据pa_num修改flag
                    String pa_num = (String) map.get("pa_num");
                    P_ADao p_aDao = new P_ADaoImpl();
                    int rowf = p_aDao.updateFlag(pa_num);
                    if (rowi + rowf > 1) {
                        System.out.println("用户" + map.get("c_id") + "到帐");
                    }
                }
            }
        } catch (SQLException e) {
            JDBCUtils.rollback();
        } finally {
            JDBCUtils.commit();
        }

    }

}

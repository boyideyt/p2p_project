package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PeDao {
    List<Map<String, Object>> findExpiration() throws SQLException;
}

package com.liu.learn.dao.impl;

import com.liu.learn.dao.UserDao;
import com.liu.learn.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    JdbcTemplate jt=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int getUsernameExist(String username) {
        String sql="select count(*) from user where name=?";
        return jt.queryForObject(sql,Integer.class,username);
    }
}

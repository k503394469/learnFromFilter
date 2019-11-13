package com.liu.learn.dao.impl;

import com.liu.learn.dao.ProvinceDao;
import com.liu.learn.domain.Province;
import com.liu.learn.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    JdbcTemplate jt=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Province> provinceList() {
        String sql="select * from province";
        return jt.query(sql,new BeanPropertyRowMapper<>(Province.class));
    }
}

package com.liu.learn.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.learn.dao.ProvinceDao;
import com.liu.learn.dao.impl.ProvinceDaoImpl;
import com.liu.learn.domain.Province;
import com.liu.learn.service.ProvinceService;
import com.liu.learn.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    ProvinceDao dao=new ProvinceDaoImpl();
    @Override
    public List<Province> provinceList() {
        return dao.provinceList();
    }

    @Override
    public String proJson() {
        Jedis jedis = JedisUtils.getJedis();
        String province = jedis.get("province");
        if (province==null||province.length()==0){
            List<Province> provinceList = provinceList();
            ObjectMapper mapper=new ObjectMapper();
            try {
                province= mapper.writeValueAsString(provinceList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("province",province);
            jedis.close();
        }
        return province;
    }

}

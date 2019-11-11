package com.liu.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.learn.domain.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JacksonDemo1 {
    @Test
    public void test1() throws Exception {
        Person p = new Person();
        p.setName("zhangsan");
        p.setAge(23);
        p.setGender("male");

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(p);

        System.out.println(json);
    }

    @Test
    public void test2() throws Exception {
        Person p = new Person();
        p.setName("zhangsan");
        p.setAge(23);
        p.setGender("male");
        p.setBir(new Date());
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(p);

        System.out.println(json);
    }
    @Test
    public void test3() throws Exception {
        Person p1 = new Person();
        p1.setName("zhangsan");
        p1.setAge(23);
        p1.setGender("male");
        p1.setBir(new Date());
        Person p2 = new Person();
        p2.setName("zhangsan");
        p2.setAge(23);
        p2.setGender("male");
        p2.setBir(new Date());
        Person p3 = new Person();
        p3.setName("zhangsan");
        p3.setAge(23);
        p3.setGender("male");
        p3.setBir(new Date());

        List<Person> p=new ArrayList<>();
        p.add(p1);
        p.add(p2);
        p.add(p3);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(p);

        System.out.println(json);
    }
}

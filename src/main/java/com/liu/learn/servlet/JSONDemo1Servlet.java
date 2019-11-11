package com.liu.learn.servlet;

import com.liu.learn.domain.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/JSONDemo1Servlet")
public class JSONDemo1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

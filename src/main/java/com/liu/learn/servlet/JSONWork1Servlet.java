package com.liu.learn.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.learn.domain.Person;
import com.liu.learn.service.UserService;
import com.liu.learn.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/jSONDemo1Servlet")
public class JSONWork1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String username = request.getParameter("name");
        UserService service = new UserServiceImpl();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();
        int result = service.getUsernameExist(username);
        if (result > 0) {
            jsonMap.put("nameExist", true);
            jsonMap.put("message", "已经存在");
        } else {
            jsonMap.put("nameExist", false);
            jsonMap.put("message", "姓名可用");
        }
        mapper.writeValue(response.getOutputStream(),jsonMap);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

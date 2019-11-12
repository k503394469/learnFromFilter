package com.liu.learn.service.impl;

import com.liu.learn.dao.UserDao;
import com.liu.learn.dao.impl.UserDaoImpl;
import com.liu.learn.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao dao=new UserDaoImpl();
    @Override
    public int getUsernameExist(String username) {
        return dao.getUsernameExist(username);
    }
}

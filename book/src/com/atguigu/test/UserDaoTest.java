package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {



    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可用!");
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserByUsernameAndPassword("admin","admin") == null) {
            System.out.println("登录失败!");
        } else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.saveUser(new User(null,"hmq","hmq123","hmq@123.com")));
    }
}
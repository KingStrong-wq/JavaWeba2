package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"bbj168","666666","bbj@168.com"));

    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"admin","admin","admin@qq.com")));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("admin"));
    }
}
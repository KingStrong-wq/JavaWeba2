package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. 调用 UserService.login() 处理业务
        User loginUser = userService.login(new User(null, username, password, null));

        // 如果等于null 说明登录失败
        if (loginUser == null) {
            //跳回登录页面
            System.out.println("用户名或密码错误!");
            req.getRequestDispatcher("/pages/user/login.html").forward(req,resp);
        } else {
            // 登录成功
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.html").forward(req,resp);
        }


    }
}

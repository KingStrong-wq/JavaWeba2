package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 优化后已经弃用此类
 * @ClassName RegistSevlet
 * @Description TUDO
 * @Author wq
 * @Date 2021/11/30 20:24
 * @Version 1.0
 */
public class RegistSevlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 2、检查 验证码是否正确 === 写死，要求验证码为abcde
        if ("abcde".equalsIgnoreCase(code)) {
            // 检查用户名是否可用
            if (userService.existsUsername(username)) {
                // 把回显信息保存到request域中
                req.setAttribute("msg","用户名已存在!!");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                System.out.println("用户名["+ username +"]已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            } else {
                userService.registUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        } else {
            // 把回显信息保存到request域中
            req.setAttribute("msg","验证码错误!!");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            // 路径： 通过项目路径book引导项目 ，通过注册按钮 发送post请求给servlet，所以工作路径其实还是项目路径下
            System.out.println("验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }




    }
}

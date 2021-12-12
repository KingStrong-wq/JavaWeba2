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
 * @ClassName UserServlet
 * @Description TUDO
 * @Author wq
 * @Date 2021/12/11 21:24
 * @Version 1.0
 */
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 处理登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. 调用 UserService.login() 处理业务
        User loginUser = userService.login(new User(null, username, password, null));

        // 如果等于null 说明登录失败
        if (loginUser == null) {

            // 把错误信息，和回显的表单信息，保存在request域中
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);


            //跳回登录页面
            System.out.println("用户名或密码错误!");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            // 登录成功
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equals(action)) {
            login(req, resp);
        } else if ("regist".equals(action)) {
            regist(req, resp);
        }
    }
}
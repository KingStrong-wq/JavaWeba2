package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @ClassName UserServlet
 * @Description TUDO
 * @Author wq
 * @Date 2021/12/11 21:24
 * @Version 1.0
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * 处理注销的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、销毁Session中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
        // 2、重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());

    }

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
            req.getSession().setAttribute("user",loginUser);
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
        // 获取Session中的验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());

        // 2、检查 验证码是否正确 === 写死，要求验证码为abcde
        if (token != null && token.equalsIgnoreCase(code)) {
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

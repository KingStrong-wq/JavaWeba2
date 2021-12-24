package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、 获取请求的参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2、 调用BookService.page(pageNo,pageSize):Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        // 3、 保存Page对象到Request域中
        req.setAttribute("page",page);
        // 4、请求转发到 pages/manger/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、 获取请求的参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);

        // 2、 调用BookService.page(pageNo,pageSize):Page 对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        // 如果有最小价格的参数，追加到分页的地址参数中
        if (min != 0) {
            sb.append("&min=").append(min);
        }
        if (max != Integer.MAX_VALUE) {
            sb.append("&max=").append(max);
        }

        page.setUrl(sb.toString());
        // 3、 保存Page对象到Request域中
        req.setAttribute("page",page);
        // 4、请求转发到 pages/manger/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }


}

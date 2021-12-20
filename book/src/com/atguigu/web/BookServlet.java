package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName BookServlet
 * @Description TUDO
 * @Author wq
 * @Date 2021/12/16 21:11
 * @Version 1.0
 */
public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数==封装成为Book对象
        try {
            Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());

            // 2、调用BookService.addBook() 保存图书
            bookService.addBook(book);
            // 3、跳到图书列表页面
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数id,图书编程
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        // 2、删除图书
        bookService.deleteBookById(id);
        // 3、重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");

    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        // 2 调用bookService.queryBookById 查询图书
        Book book = bookService.queryBookById(id);
        // 3 保存图书到Request域中
        req.setAttribute("book",book);

        // 4 请求转发到。 pages/manager/book_edit.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 2、调用BookService.updateBook(book) 修改图书
        bookService.updateBook(book);
        // 3、重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过 BookService 查询全部图书
        List<Book> books = bookService.queryBooks();
        // 把全部图书保存到Request域中
        req.setAttribute("books",books);
        // 请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}

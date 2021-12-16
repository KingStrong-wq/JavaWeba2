package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"三体","刘慈欣",new BigDecimal(86),60,3000,""));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(20,"三体","刘慈欣",new BigDecimal(86),60,3000,""));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(19));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookDao.queryBooks());
    }
}
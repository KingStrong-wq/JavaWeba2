package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * 使用对数据库的基础操作要如何操作 User 信息
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 如果返回null 说明没有用户
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null 说明用户名或密码错误
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);





}

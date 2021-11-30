package com.atguigu.service;

import com.atguigu.pojo.User;

public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null，说明登录失败
     */
    public User login(User user);

    /**
     * 检查 用户名是否可用
     * @param name
     * @return 返回true表示用户名已存在，返回false表示用户名不存在
     */
    public boolean existsUsername(String name);

}

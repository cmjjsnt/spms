package com.huihuan.jerrys.shiro.server.dao;

import java.util.List;

import com.huihuan.jerrys.shiro.entity.User;

/**
 * 
 * 用户管理
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public interface UserDao {

    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(Long userId);

    User findOne(Long userId);

    List<User> findAll();

    User findByUsername(String username);

}

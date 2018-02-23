package com.springboot.service;

import com.springboot.pojo.User;

import java.util.List;

/**
 * @author  liumingxin
 * @create   2018/2/11 15:10
 * @desc
 */
public interface UserService {
    public List<User> findAll();

    void setUser(User user);
}

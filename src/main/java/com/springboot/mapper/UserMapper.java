package com.springboot.mapper;

import com.springboot.pojo.User;

import java.util.List;

/**
 * Created by LYJ on 2017/9/12.
 */
public interface UserMapper {
    public List<User> findAll();

}

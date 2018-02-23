package com.springboot.service;

import com.alibaba.fastjson.JSON;
import com.springboot.mapper.UserMapper;
import com.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by LYJ on 2017/9/12.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired    //userMapper对象应该由spring创建
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void setUser(User user) {
        try {
            OutputStream out =new FileOutputStream("user.properties");
            
            Properties properties=new Properties();
            String user1= JSON.toJSONString(user);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}

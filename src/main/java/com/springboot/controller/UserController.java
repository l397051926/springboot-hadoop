package com.springboot.controller;

import com.springboot.pojo.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by LYJ on 2017/9/12.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model){

        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        //经过视图解析器跳转页面
        return  "userList";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String Hello(){
        return "hello";
    }

    @RequestMapping("hello2")
    public String hello2(){
        return "index";
    }

    @RequestMapping
    @ResponseBody
    public void setUser(Integer id, String name, Integer age, String sex) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setSex(sex);
        userService.setUser(user);
    }
}

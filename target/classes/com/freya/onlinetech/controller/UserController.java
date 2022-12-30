package com.freya.onlinetech.controller;

import com.freya.onlinetech.mapper.UserMapper;
import com.freya.onlinetech.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<User> queryUserList()
    {
        List<User> userList = userMapper.queryUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    @GetMapping("/addUser")
    public String addUser(){
        userMapper.addUser(new User(5,"昂啊","3200"));
        return "OK";
    }

    @GetMapping("/updateUser")
    public String updateUser()
    {
        userMapper.updateUser(new User(5,"aa","234"));
        return "OK";
    }

    //根据id删除用户
    @GetMapping("/deleteUser")
    public String deleteUser()
    {
        userMapper.deleteUser(5);
        return "OK";
    }


}

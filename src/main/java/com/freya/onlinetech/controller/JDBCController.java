package com.freya.onlinetech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

//    //查询数据库的所有信息
//    @GetMapping("/userList")
//    public List<Map<String,Object>> userList(){
//        String sql = "select * from users";
//        List<Map<String,Object>> list_maps = jdbcTemplate.queryForList(sql);
//        return list_maps;
//    }
//
//    //向数据库增加值
//    @GetMapping("/addUser")
//    public String addUser()
//    {
//        String sql = "insert into jdbc.users(id,name,password,email) values(4,'小明','123456','123@qq.com')";
//        jdbcTemplate.update(sql);
//        return "add-ok";
//    }
//
//    @GetMapping("/updateUser/{id}")
//    public String updateUser(@PathVariable("id") int id)
//    {
//        String sql = "update jdbc.users set name=?,password=? where id="+id;
//        //封装
//        Object[] objects = new Object[2];
//        objects[0] = "小明";
//        objects[1] = "zzzzz";
//        jdbcTemplate.update(sql,objects);
//        return "update-ok";
//    }
//
//    @GetMapping("/deleteUser/{id}")
//    public String deleteUser(@PathVariable("id") int id)
//    {
//        String sql = "delete from jdbc.users where id=?";
//        jdbcTemplate.update(sql,id);
//        return "delete-ok";
//    }
}

package com.freya.onlinetech.controller;

import com.freya.onlinetech.pojo.StudentInfo;
import com.freya.onlinetech.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ChatController {
    @Autowired
    StudentService studentService;

    //登录成功后跳转到聊天室，发送给前端用户名
    @RequestMapping({"/chatroom"})
    public String toChat(@RequestParam("student_id") int student_id,Model model){
            //model.addAttribute("username", user.getUsername());
        StudentInfo studentInfo = studentService.getStudentById(student_id);
        List<String> list = new ArrayList<>();
        list.add("/img/avatars/avatar-all.jpg");
        list.add("/img/avatars/avatar-3336418.jpg");
        list.add("/img/avatars/avatar-1102170112.jpg");
        list.add("/img/avatars/avatar-1102170113.jpg");
        list.add("/img/avatars/avatar-1102170110.jpg");
        if(student_id == 1102170115)
        {
            model.addAttribute("username", "资资");
            //是资资的话就加上清风的头像
            list.add("/img/avatars/avatar-1102170114.jpg");
        }else if(student_id == 1102170114){
            //是清风的话就加上资资的头像
            model.addAttribute("username", "清风");
            list.add("/img/avatars/avatar-1102170115.jpg");
        }
        model.addAttribute("avatars",list);
        System.out.println("ChatController->chatroom");
        return "chat/chatroom";
    }

}

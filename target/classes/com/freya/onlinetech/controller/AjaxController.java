package com.freya.onlinetech.controller;

import com.freya.onlinetech.service.StudentFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AjaxController {
    @Autowired
    StudentFollowService studentFollowService;

    @PostMapping("/student/followTeacher")
    @ResponseBody
    public Object studentFollowTeacher(HttpServletRequest request){
        //request.getParameter("str")) 可以根据所示方法获取ajax的键值对
        //System.out.println("studentFollowTeacher->"+request.getParameter("student_id"));
        System.out.println("AjaxController->studentFollowTeacher");
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
        Map<String,Object> map = new HashMap<>();
        //先判断目前是关注的状态还是未关注的状态
        List<Integer> FollowedTeachersID = studentFollowService.getFollowedTeacherID(student_id);
        //向model中加入学生和教师的ID
        //如果已经关注了这个教师
        if (FollowedTeachersID.indexOf(teacher_id) != -1)
        {
            //移除关注
            int res = studentFollowService.studentFollowCancel(student_id,teacher_id);
            if(res!=1)
            {
                map.put("followStatus","yes");
                map.put("error","yes");
            } else{
                map.put("followStatus","no");
                map.put("error","no");
            }
        }else{
            //添加关注
            int res = studentFollowService.addStudentFollow(student_id,teacher_id);
            if(res!=1){
                //执行失败了
                map.put("followStatus","no");
                map.put("error","yes");
            }else{
                map.put("followStatus","yes");
                map.put("error","no");
            }
        }

        return map;
    }
}

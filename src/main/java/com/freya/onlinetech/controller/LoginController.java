package com.freya.onlinetech.controller;

import com.freya.onlinetech.pojo.*;
import com.freya.onlinetech.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    StudentFollowService studentFollowService;
    @Autowired
    VideoService videoService;
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    SchoolService schoolService;

    @RequestMapping("/user/login")
    public String login(
            @RequestParam("id") int id,
            @RequestParam("password") String password,
            Model model, HttpSession session)
    {
        System.out.println("LoginController->login");
        //具体的业务
        if(password.equals("123456"))
        {
            session.setAttribute("student_id",id);
            System.out.println("LoginController->login->if->1");
            List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(id);
            model.addAttribute("teacherFollows",teacherFollows);
            session.setAttribute("teacherFollows",teacherFollows);
            //视频信息
            List<VideoInfo> videoFollows = videoService.getFollowVideo(id);
            model.addAttribute("videoFollows",videoFollows);
            //getRecommendCourse
            List<Course> AllCourses = courseService.getRecommendCourse(id);
            model.addAttribute("AllCourses",AllCourses);
            //得到学生的信息并添加到model
            StudentInfo studentInfo = studentService.getStudentById(id);
            model.addAttribute("studentInfo",studentInfo);
            //把学生的ID添加到model中
            model.addAttribute("student_id",id);
            //推荐教师的信息
            List<TeacherAndSchool> RecommendTeachers = studentFollowService.getRecommendTeacher(id);
            model.addAttribute("RecommendTeachers",RecommendTeachers);
            System.out.println("RecommendTeachers:"+RecommendTeachers);
            //增加学校的信息
            List<School> schools = schoolService.getSchoolLim5();
            model.addAttribute("schools",schools);
            return "student/student-index";
        }else{
            //告诉用户，你登录失败了
            model.addAttribute("msg","用户名或者密码错误！");
            return "index";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/index.html";
    }

    @RequestMapping({"/studentRegister"})
    public String registerPage(Model model)
    {
        return "register/studentRegister";
    }

}

package com.freya.onlinetech.controller;

import com.freya.onlinetech.mapper.StudentInfoMapper;
import com.freya.onlinetech.pojo.*;
import com.freya.onlinetech.service.CourseService;
import com.freya.onlinetech.service.SchoolService;
import com.freya.onlinetech.service.StudentFollowService;
import com.freya.onlinetech.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class SchoolController {
    @Autowired
    SchoolService schoolService;
    @Autowired
    StudentFollowService studentFollowService;
    @Autowired
    StudentInfoMapper studentInfoMapper;

    //所有的学校的信息的展示
    @RequestMapping("/school/recommend")
    public String schoolRecommend(
            @RequestParam("student_id") int student_id,
            Model model, HttpSession session)
    {
        //关注的老师的列表
        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(student_id);
        model.addAttribute("teacherFollows",teacherFollows);
        //把学生的ID添加到model中
        model.addAttribute("student_id",student_id);
        StudentInfo studentInfo = studentInfoMapper.queryStudentById(student_id);
        model.addAttribute("studentInfo",studentInfo);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        //展示学生关注的所有教师
        List<TeacherAndSchool> allFollowedTeachers = studentFollowService.getAllFollowedTeacher(student_id);
        model.addAttribute("allFollowedTeachers",allFollowedTeachers);
        //展示全部的学校
        List<School> Allschools = schoolService.getAllSchools();
        model.addAttribute("Allschools",Allschools);
        return "school/schoolRecommend";
    }


    @RequestMapping("/school/single")
    public String schoolSingle(
            @RequestParam("student_id") int student_id,
            @RequestParam("school_id") int school_id,
            Model model, HttpSession session)
    {
        //关注的老师的列表
        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(student_id);
        model.addAttribute("teacherFollows",teacherFollows);
        //把学生的ID添加到model中
        model.addAttribute("student_id",student_id);
        StudentInfo studentInfo = studentInfoMapper.queryStudentById(student_id);
        model.addAttribute("studentInfo",studentInfo);
        //学校的id school_id
        School school = schoolService.getSchoolInfo(school_id);
        model.addAttribute("school",school);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);

        return "school/schoolSingle";
    }

    //List<School> getSchoolLim5()
}

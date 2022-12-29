package com.freya.onlinetech.controller;

import com.freya.onlinetech.mapper.StudentInfoMapper;
import com.freya.onlinetech.pojo.School;
import com.freya.onlinetech.pojo.StudentInfo;
import com.freya.onlinetech.pojo.TeacherInfo;
import com.freya.onlinetech.pojo.VideoInfo;
import com.freya.onlinetech.service.SchoolService;
import com.freya.onlinetech.service.StudentFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    StudentFollowService studentFollowService;
    @Autowired
    SchoolService schoolService;

    private HttpSession session;
    private Model model;
    private int student_id;

    @RequestMapping({"/student/register"})
    public String StudentRegister(StudentInfo studentInfo)
    {
        studentInfoMapper.addStudent(studentInfo);
        return "index";
    }

    @RequestMapping({"/student/studentInfo"})
    public String StudentInfoShow(HttpSession session, Model model,
                                  @RequestParam("student_id") int student_id)
    {
        //关注的老师的列表
        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(student_id);
        model.addAttribute("teacherFollows",teacherFollows);
        //把学生的ID添加到model中
        model.addAttribute("student_id",student_id);
        //这个地方用username会出错，因为改变了username之后，那个还没改变
        System.out.println("StudentController->StudentInfoShow");
        StudentInfo studentInfo = studentInfoMapper.queryStudentById(student_id);
        System.out.println(studentInfo);
        model.addAttribute("studentInfo",studentInfo);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        return "student/student-info";
        //return "student/studentInfo";
    }

    //学习数据
    @RequestMapping({"/student/studentData"})
    public String StudentDataShow(HttpSession session, Model model,
                                  @RequestParam("student_id") int student_id)
    {
        this.session = session;
        this.model = model;
        this.student_id = student_id;
        //关注的老师的列表
        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(student_id);
        model.addAttribute("teacherFollows",teacherFollows);
        //把学生的ID添加到model中
        model.addAttribute("student_id",student_id);
        //这个地方用username会出错，因为改变了username之后，那个还没改变
        System.out.println("StudentController->StudentInfoShow");
        StudentInfo studentInfo = studentInfoMapper.queryStudentById(student_id);
        System.out.println(studentInfo);
        model.addAttribute("studentInfo",studentInfo);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        return "student/student-data";
        //return "student/student-dataInfo";
    }

    @RequestMapping({"/student/updateStudentInfo"})
    public String StudnetInfoUpdate(StudentInfo studentInfo)
    {
        studentInfoMapper.updateStudent(studentInfo);
        int student_id = studentInfo.getId();
        return "redirect:/student/studentInfo?student_id="+student_id;
    }

    @RequestMapping({"/student/UpdateInfoCancel"})
    public String UpdateInfoCancel(@RequestParam("student_id") int student_id)
    {
        System.out.println("student->UpdateInfoCancel");
        return "redirect:/student/studentInfo?student_id="+student_id;
        //return "redirect:/student/studentInfo";
    }

//    @RequestMapping({"/student/followTeacher"})
//    public void FollowTeacher(
//            @RequestParam("course_id") int course_id,
//            @RequestParam("video_id") int video_id,
//            HttpSession session,
//            Model model
//    )
//    {
//
//    }
}

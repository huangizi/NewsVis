package com.freya.onlinetech.controller;

import com.alibaba.fastjson.JSONObject;
import com.freya.onlinetech.pojo.*;
import com.freya.onlinetech.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class IndexController {
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
    @Autowired
    NewsService newsService;
    
    @RequestMapping({"/","/index.html"})
    public String index(Model model)
    {
        //return "public";
        List<NewsTitle> newsTitle = newsService.getNewsTitle();
        model.addAttribute("newsTitle",newsTitle);
        System.out.println(newsTitle);
        //词云统计
        //Map<String,Object> WordCloudMap = new HashMap<>();
        Map<String, Integer> WordCloudMap = newsService.getCloudText();
//        WordCloudMap.put("az",3);
//        WordCloudMap.put("b",6);
//        WordCloudMap.put("c",8);
//        WordCloudMap.put("d",2);
       //JSONObject json = new JSONObject(WordCloudMap);
        //System.out.println(json);
//        List<String> WordCloud_key = new ArrayList<>();
//        for (String key:WordCloudMap.keySet())
//        {
//            WordCloud_key.add('"'+key+'"');
//        }
//        System.out.println(WordCloud_key);
//        WordCloud a = new WordCloud("a",4);
//        WordCloudSet.add(a);
        //JSONObject WordCloud = new JSONObject(WordCloudMap);
//        Set<String> WordCloud_key = WordCloud.keySet();
//        System.out.println(WordCloud_key);
//        Collection<Integer> WordCloud_values = WordCloudMap.values();
//        model.addAttribute("WordCloud_key",WordCloud_key);
//        model.addAttribute("WordCloud_values",WordCloud_values);
        model.addAttribute("WordCloud",WordCloudMap);
        return "index";
        //return "redirect:/chatroom";
    }

    @RequestMapping("/news")
    public String returnNews()
    {
        return "public";
        //return "redirect:/chatroom";
    }

    @RequestMapping("/studentIndex")
    public String studentIndex(@RequestParam("student_id") int student_id,
                               HttpSession session,
                               Model model)
    {
        int id = student_id;
//        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(id);
//        model.addAttribute("teacherFollows",teacherFollows);
//        session.setAttribute("teacherFollows",teacherFollows);
//        List<VideoInfo> videoFollows = videoService.getFollowVideo(id);
//        model.addAttribute("videoFollows",videoFollows);
//        //得到学生的信息并添加到model
//        StudentInfo studentInfo = studentService.getStudentById(id);
//        model.addAttribute("studentInfo",studentInfo);
//        //把学生的ID添加到model中
//        model.addAttribute("student_id",id);
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
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        //推荐教师的信息
        List<TeacherAndSchool> RecommendTeachers = studentFollowService.getRecommendTeacher(id);
        model.addAttribute("RecommendTeachers",RecommendTeachers);
        System.out.println("RecommendTeachers:"+RecommendTeachers);
        //返回主页的视图
        return "student/student-index";
    }
}

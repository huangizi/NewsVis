package com.freya.onlinetech.controller;

import com.freya.onlinetech.pojo.*;
import com.freya.onlinetech.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    StudentFollowService studentFollowService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    VideoService videoService;
    @Autowired
    CourseService courseService;
    @Autowired
    SchoolService schoolService;
    @Autowired
    StudentService studentService;
    @Autowired
    CommentsService commentsService;

    private int pageActive = 1;
    private int teacher_id;
    private int student_id;
    @RequestMapping("/teacher/singleChannel")
    public String showTeacherChannel(
            @RequestParam("teacher_id") int teacher_id,
            @RequestParam(value = "student_id") int student_id,
            Model model)
    {
        //设置参数值
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        //
        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(student_id);
        model.addAttribute("teacherFollows",teacherFollows);
        TeacherInfo teacherInfo = teacherService.getTeacherInfobyId(teacher_id);
        model.addAttribute("teacherInfo",teacherInfo);
        System.out.println(teacherInfo);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        //System.out.println("kkk"+courseService.queryAllTeacherCourse(teacher_id).isEmpty());
        //如果这个教师有课程
        if (courseService.queryAllTeacherCourse(teacher_id).isEmpty() == false)
        {
            //全部视频
            int teacherVideoNum = videoService.getTeacherVideoNum(teacher_id);
            int videoPageNum = teacherVideoNum/8+1;
            model.addAttribute("VideoPageNum",videoPageNum);
            List<VideoInfo> videoInfos = videoService.getTeacherVideo(teacher_id,pageActive,8);
            model.addAttribute("Teachervideos",videoInfos);
            model.addAttribute("PageNumList",makeSequence(1,videoPageNum));
            model.addAttribute("pageActive",pageActive);
            //最新视频
            List<VideoInfo> latestVideos = videoService.getLatestTeacherVideo(teacher_id);
            model.addAttribute("latestVideos",latestVideos);
            //最新课程
            Course latestCourse = courseService.getLatestCourse(teacher_id);
            model.addAttribute("latestCourse",latestCourse);
            //最新课程的第一节视频
            VideoInfo latestCourseVideo = videoService.queryLatestCourseVideo(latestCourse.getCourse_id());
            model.addAttribute("latestCourseVideo",latestCourseVideo);
            //全部课程
            List<Course> teacherAllCourse = courseService.queryAllTeacherCourse(teacher_id);
            model.addAttribute("teacherAllCourse",teacherAllCourse);
//        List<VideoInfo> CourseVideos = new ArrayList<>();
//        for (Course course : teacherAllCourse) {
//            VideoInfo CourseVideo = videoService.queryLatestCourseVideo(course.getCourse_id());
//            CourseVideos.add(CourseVideo);
//        }

            //把学生的ID添加到model中
            model.addAttribute("student_id",student_id);
            //获取教师的学校信息
            School schoolInfo = schoolService.getSchoolInfo(teacherInfo.getTeacher_school_id());
            model.addAttribute("school_info",schoolInfo);
            //学生是否已经关注
            List<Integer> FollowedTeachersID = studentFollowService.getFollowedTeacherID(student_id);
            //向model中加入学生和教师的ID
            model.addAttribute("teacher_id",teacher_id);
            //如果已经关注了这个教师
            if (FollowedTeachersID.indexOf(teacher_id) != -1)
            {
                model.addAttribute("teacherIsFollow",true);
            }else{
                model.addAttribute("teacherIsFollow",false);
            }
            //得到学生的信息并添加到model
            StudentInfo studentInfo = studentService.getStudentById(student_id);
            model.addAttribute("studentInfo",studentInfo);
            return "teacher/single-channel";
        }else{
            //把学生的ID添加到model中
            model.addAttribute("student_id",student_id);
            //获取教师的学校信息
            School schoolInfo = schoolService.getSchoolInfo(teacherInfo.getTeacher_school_id());
            model.addAttribute("school_info",schoolInfo);
            //学生是否已经关注
            List<Integer> FollowedTeachersID = studentFollowService.getFollowedTeacherID(student_id);
            //向model中加入学生和教师的ID
            model.addAttribute("teacher_id",teacher_id);
            //如果已经关注了这个教师
            if (FollowedTeachersID.indexOf(teacher_id) != -1)
            {
                model.addAttribute("teacherIsFollow",true);
            }else{
                model.addAttribute("teacherIsFollow",false);
            }
            //得到学生的信息并添加到model
            StudentInfo studentInfo = studentService.getStudentById(student_id);
            model.addAttribute("studentInfo",studentInfo);
            return "teacher/single-channel-noCourse";
            //return "student/student-index";
        }
    }


    //返回教师的全部视频
    @RequestMapping("/teacher/allVideo")
    public String showTeacherAllVideo(
            @RequestParam("teacher_id") int teacher_id,
            @RequestParam(value = "student_id") int student_id,
            Model model)
    {
        //设置参数值
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        //
        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(student_id);
        model.addAttribute("teacherFollows",teacherFollows);
        TeacherInfo teacherInfo = teacherService.getTeacherInfobyId(teacher_id);
        model.addAttribute("teacherInfo",teacherInfo);
        System.out.println(teacherInfo);
        //获取教师的学校信息
        School schoolInfo = schoolService.getSchoolInfo(teacherInfo.getTeacher_school_id());
        model.addAttribute("school_info",schoolInfo);
        //向model中加入值
        model.addAttribute("teacher_id",this.teacher_id);
        model.addAttribute("student_id",this.student_id);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        //全部视频
        int teacherVideoNum = videoService.getTeacherVideoNum(teacher_id);
        int videoPageNum = teacherVideoNum/8+1;
        model.addAttribute("VideoPageNum",videoPageNum);
        List<VideoInfo> videoInfos = videoService.getTeacherVideo(teacher_id,pageActive,8);
        model.addAttribute("Teachervideos",videoInfos);
        model.addAttribute("PageNumList",makeSequence(1,videoPageNum));
        model.addAttribute("pageActive",pageActive);
        //学生是否已经关注了这个老师
        List<Integer> FollowedTeachersID = studentFollowService.getFollowedTeacherID(student_id);
        //如果已经关注了这个教师
        if (FollowedTeachersID.indexOf(teacher_id) != -1)
        {
            model.addAttribute("teacherIsFollow",true);
        }else{
            model.addAttribute("teacherIsFollow",false);
        }
        return "teacher/single-channel-allVideo";
    }

    ///teacher/updateTeacherAllVideo
    //教师的全部视频，更新页码
    @RequestMapping("/teacher/updateTeacherAllVideo")
    public String updateTeacherAllVideo(@RequestParam("currentPage") int currentPage,
                                        @RequestParam("teacher_id") int teacher_id,
                                        @RequestParam(value = "student_id") int student_id)
    {
        pageActive = currentPage;
        return "redirect:/teacher/allVideo?student_id="+student_id+"&teacher_id="+teacher_id;
    }

    //教师主页的全部视频，更新了页码
    @RequestMapping("/teacher/updateVideo")
    public String updateTeacherVideo(@RequestParam("currentPage") int currentPage, RedirectAttributes attr)
    {
        pageActive = currentPage;
        attr.addAttribute("teacher_id",this.teacher_id);
        attr.addAttribute("student_id",this.student_id);
        return "redirect:/teacher/singleChannel";
    }

    public List<Integer> makeSequence(int begin, int end) {
        List<Integer> ret = new ArrayList<>(end - begin + 1);
        for (int i = begin; i <= end; i++) {
            ret.add(i);
        }
        return ret;
    }

    //展示学生关注的所有的教师的列表
    @RequestMapping("/teacher/browseChannals")
    public String browseChannels(@RequestParam("student_id") int student_id,Model model, HttpSession session)
    {
        //别从session中得到值，会出错，还是用传参的方式比较好
        this.student_id = student_id;
        int id = student_id;
        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(id);
        model.addAttribute("teacherFollows",teacherFollows);
        //展示学生关注的所有教师
        List<TeacherAndSchool> allFollowedTeachers = studentFollowService.getAllFollowedTeacher(id);
        model.addAttribute("allFollowedTeachers",allFollowedTeachers);
        //把学生的ID添加到model中
        model.addAttribute("student_id",id);
        //得到学生的信息并添加到model
        StudentInfo studentInfo = studentService.getStudentById(student_id);
        model.addAttribute("studentInfo",studentInfo);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        return "teacher/browse-all-follows";
    }

    //展示学生关注的所有的教师的列表
    @RequestMapping("/teacher/browseAllTeacher")
    public String browseAllTeacher(@RequestParam("student_id") int student_id,Model model, HttpSession session)
    {
        //别从session中得到值，会出错，还是用传参的方式比较好
        this.student_id = student_id;
        int id = student_id;
        List<TeacherInfo> teacherFollows = studentFollowService.getAllTeachers();
        model.addAttribute("teacherFollows",teacherFollows);
        //展示学生关注的所有教师
        List<TeacherAndSchool> allFollowedTeachers = studentFollowService.getAllTeachersInfo();
        model.addAttribute("allFollowedTeachers",allFollowedTeachers);
        //把学生的ID添加到model中
        model.addAttribute("student_id",id);
        //得到学生的信息并添加到model
        StudentInfo studentInfo = studentService.getStudentById(student_id);
        model.addAttribute("studentInfo",studentInfo);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        return "teacher/browse-all-teachers";
    }

    //展示教师的评价
    @RequestMapping("/teacher/comments")
    public String getTeacherComments(
            @RequestParam("teacher_id") int teacher_id,
            @RequestParam("student_id") int student_id,
            Model model)
    {
        //关注的老师的列表
        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(student_id);
        model.addAttribute("teacherFollows",teacherFollows);
        //把学生的ID添加到model中
        model.addAttribute("student_id",student_id);
        //得到学生的信息并添加到model
        StudentInfo studentInfo = studentService.getStudentById(student_id);
        model.addAttribute("studentInfo",studentInfo);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        //得到教师的信息添加进来
        TeacherInfo teacherInfo = teacherService.getTeacherInfobyId(teacher_id);
        model.addAttribute("teacherInfo",teacherInfo);
        //获取学校的信息
        School schoolInfo = schoolService.getSchoolInfo(teacherInfo.getTeacher_school_id());
        model.addAttribute("school_info",schoolInfo);
        //--------根据教师的信息获取多个课程的评论
        //获取课程评论信息
        List<CommentsRoot> commentsRoots = commentsService.getTeacherComments(teacher_id);
        int commentsSize = commentsRoots.size();
        model.addAttribute("commentsSize",commentsSize);
        model.addAttribute("commentsRoot",commentsRoots);
        //添加标记，可以让选中的那一栏亮起
        model.addAttribute("TopBarActive",'4');
        //学生是否已经关注了这个老师
        List<Integer> FollowedTeachersID = studentFollowService.getFollowedTeacherID(student_id);
        //向model中加入学生和教师的ID
           model.addAttribute("teacher_id",teacher_id);
        //如果已经关注了这个教师
        if (FollowedTeachersID.indexOf(teacher_id) != -1)
        {
            model.addAttribute("teacherIsFollow",true);
        }else{
            model.addAttribute("teacherIsFollow",false);
        }
        return "teacher/teacherComments";
    }
}



package com.freya.onlinetech.controller;

import com.freya.onlinetech.mapper.CommentsMapper;
import com.freya.onlinetech.mapper.CourseMapper;
import com.freya.onlinetech.mapper.StudentInfoMapper;
import com.freya.onlinetech.pojo.*;
import com.freya.onlinetech.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentFollowService studentFollowService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private  SchoolService schoolService;
    //视频学习界面
    @RequestMapping("/video/detail")
    public String getCourseDetail(
            @RequestParam("course_id") int course_id,
            @RequestParam("video_id") int video_id,
            @RequestParam("student_id") int student_id,
            HttpSession session,
            Model model)
    {
        //获得该视频属于的课程的信息，以及课程标签等的信息，在网页上展示
        Course course = courseService.getCourseDetail(course_id);
        model.addAttribute("courseDetail",course);
        List<String> CourseTags = courseService.getCourseTag(course_id);
        model.addAttribute("CourseTags",CourseTags);
        //查询该视频的评论
        List<CommentsRoot> commentsRoots = commentsService.getCommentsRoot(course_id);
        int commentsSize = commentsRoots.size();
        model.addAttribute("commentsSize",commentsSize);
        model.addAttribute("commentsRoot",commentsRoots);
        //获得该视频的信息在网页上展示
        VideoInfo videoInfo = videoService.getVideoById(video_id);
        model.addAttribute("videoInfo",videoInfo);
        //获得视频的赞的比率
        //int good_rate = (videoInfo.getVideo_good_num()*100/(videoInfo.getVideo_bad_num()+videoInfo.getVideo_good_num()));
        int good_rate = VideoGoodRate(videoInfo.getVideo_bad_num(),videoInfo.getVideo_good_num());
        model.addAttribute("video_good_rate",good_rate);
        //获得该视频的教师的信息
        int teacher_id = videoInfo.getVideo_teacher_id();
        TeacherInfo teacherInfo = teacherService.getTeacherInfobyId(teacher_id);
        model.addAttribute("teacherInfo",teacherInfo);
        //得到学生关注的教师列表
        //int student_id = (int) session.getAttribute("student_id");
        List<Integer> FollowedTeachersID = studentFollowService.getFollowedTeacherID(student_id);
        //向model中加入学生和教师的ID
        model.addAttribute("student_id",student_id);
        model.addAttribute("teacher_id",teacher_id);
        //如果已经关注了这个教师
        if (FollowedTeachersID.indexOf(teacher_id) != -1)
        {
            model.addAttribute("teacherIsFollow",true);
        }else{
            model.addAttribute("teacherIsFollow",false);
        }
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
        //关注的视频信息展示出来
        List<VideoInfo> videoFollows = videoService.getFollowVideo(student_id);
        model.addAttribute("videoFollows",videoFollows);
        return "video/videodetail";
    }

    @RequestMapping("/course/detail")
    public String getCourseDetail(
            @RequestParam("course_id") int course_id,
            @RequestParam("student_id") int student_id,
            Model model)
    {
        CourseVideo courseVideo = courseService.queryCourseVideo(course_id);
        model.addAttribute("courseVideo", courseVideo);
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
        TeacherInfo teacherInfo = teacherService.getTeacherInfobyId(courseService.queryCourseTeacher(course_id));
        model.addAttribute("teacherInfo",teacherInfo);
        //把课程的信息添加进去
        Course courseInfo = courseService.getCourseDetail(course_id);
        model.addAttribute("courseInfo",courseInfo);
        //关注的视频
        model.addAttribute("TopBarActive",'1');
        //得到学生关注的教师列表
        //int student_id = (int) session.getAttribute("student_id");
        List<Integer> FollowedTeachersID = studentFollowService.getFollowedTeacherID(student_id);
        //向model中加入学生和教师的ID
        int teacher_id = courseInfo.getCourse_teacher_id();
        model.addAttribute("teacher_id",teacher_id);
        //如果已经关注了这个教师
        if (FollowedTeachersID.indexOf(teacher_id) != -1)
        {
            model.addAttribute("teacherIsFollow",true);
        }else{
            model.addAttribute("teacherIsFollow",false);
        }
        return "video/coursedetail";
    }


    //查询课程的评论
    //th:href="@{/course/comments(course_id=${courseVideo.getCourse_id()},student_id=${student_id})}"
    @RequestMapping("/course/comments")
    public String getCourseComments(
            @RequestParam("course_id") int course_id,
            @RequestParam("student_id") int student_id,
            Model model)
    {
        //获取课程的信息
        CourseVideo courseVideo = courseService.queryCourseVideo(course_id);
        model.addAttribute("courseVideo", courseVideo);
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
        TeacherInfo teacherInfo = teacherService.getTeacherInfobyId(courseService.queryCourseTeacher(course_id));
        model.addAttribute("teacherInfo",teacherInfo);
        //把课程的信息添加进去
        Course courseInfo = courseService.getCourseDetail(course_id);
        model.addAttribute("courseInfo",courseInfo);
        //获取课程评论信息
        List<CommentsRoot> commentsRoots = commentsService.getCommentsRoot(course_id);
        int commentsSize = commentsRoots.size();
        model.addAttribute("commentsSize",commentsSize);
        model.addAttribute("commentsRoot",commentsRoots);
        //添加标记，可以让选中的那一栏亮起
        model.addAttribute("TopBarActive",'4');
        //学生是否已经关注了这个老师
        List<Integer> FollowedTeachersID = studentFollowService.getFollowedTeacherID(student_id);
        //向model中加入学生和教师的ID
        int teacher_id = courseInfo.getCourse_teacher_id();
        model.addAttribute("teacher_id",teacher_id);
        //如果已经关注了这个教师
        if (FollowedTeachersID.indexOf(teacher_id) != -1)
        {
            model.addAttribute("teacherIsFollow",true);
        }else{
            model.addAttribute("teacherIsFollow",false);
        }
        return "video/courseComments";
    }

    //课件的展示页
    @RequestMapping("/course/courseware")
    public String getCourseware(
            @RequestParam("course_id") int course_id,
            @RequestParam("student_id") int student_id,
            Model model)
    {
        CourseVideo courseVideo = courseService.queryCourseVideo(course_id);
        model.addAttribute("courseVideo", courseVideo);
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
        TeacherInfo teacherInfo = teacherService.getTeacherInfobyId(courseService.queryCourseTeacher(course_id));
        model.addAttribute("teacherInfo",teacherInfo);
        //把课程的信息添加进去
        Course courseInfo = courseService.getCourseDetail(course_id);
        model.addAttribute("courseInfo",courseInfo);
        //关注的视频
        model.addAttribute("TopBarActive",'1');
        //得到学生关注的教师列表
        //int student_id = (int) session.getAttribute("student_id");
        List<Integer> FollowedTeachersID = studentFollowService.getFollowedTeacherID(student_id);
        //向model中加入学生和教师的ID
        int teacher_id = courseInfo.getCourse_teacher_id();
        model.addAttribute("teacher_id",teacher_id);
        //如果已经关注了这个教师
        if (FollowedTeachersID.indexOf(teacher_id) != -1)
        {
            model.addAttribute("teacherIsFollow",true);
        }else{
            model.addAttribute("teacherIsFollow",false);
        }
        return "video/courseWare";
    }

    //返回全部未看的视频
    @RequestMapping("/course/follow")
    public String getFollowCourse(
            @RequestParam("student_id") int student_id,
            HttpSession session,
            Model model)
    {
        //关注的老师的列表
        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(student_id);
        model.addAttribute("teacherFollows",teacherFollows);
        session.setAttribute("teacherFollows",teacherFollows);
        List<VideoInfo> videoFollows = videoService.getFollowVideo(student_id);
        model.addAttribute("videoFollows",videoFollows);
        //把学生的ID添加到model中
        model.addAttribute("student_id",student_id);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        //添加学生的信息
        StudentInfo studentInfo = studentInfoMapper.queryStudentById(student_id);
        model.addAttribute("studentInfo",studentInfo);
        return "video/browse-follow-video";
    }

    //返回参与的全部课程列表
    @RequestMapping("/course/join")
    public String getJoinCourse(
            @RequestParam("student_id") int student_id,
            HttpSession session,
            Model model)
    {
        //关注的老师的列表
        List<TeacherInfo> teacherFollows = studentFollowService.getStudentFollows(student_id);
        model.addAttribute("teacherFollows",teacherFollows);
        session.setAttribute("teacherFollows",teacherFollows);
        //这里查询的还是关注的课程
        List<VideoInfo> videoFollows = videoService.getFollowVideo(student_id);
        model.addAttribute("videoFollows",videoFollows);
        //把学生的ID添加到model中
        model.addAttribute("student_id",student_id);
        //展示学生参与的所有课程
        List<Course> courses = courseService.getCoursesByStuID(student_id);
        model.addAttribute("allCourses",courses);
        //得到学生的信息并添加到model
        StudentInfo studentInfo = studentService.getStudentById(student_id);
        model.addAttribute("studentInfo",studentInfo);
        //增加学校的信息
        List<School> schools = schoolService.getSchoolLim5();
        model.addAttribute("schools",schools);
        return "student/student-join-course";
        //return "video/browse-join-video";
    }

    int VideoGoodRate(int Video_bad_num,int Video_good_num)
    {
        if((Video_bad_num + Video_good_num) == 0)
        {
            return 100;
        }else{
            return Video_good_num*100/(Video_good_num+Video_bad_num);
        }
    }
}

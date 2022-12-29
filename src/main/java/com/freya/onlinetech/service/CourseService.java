package com.freya.onlinetech.service;

import com.freya.onlinetech.mapper.CourseMapper;
import com.freya.onlinetech.mapper.CourseTagMapper;
import com.freya.onlinetech.mapper.SchoolMapper;
import com.freya.onlinetech.mapper.TeacherInfoMapper;
import com.freya.onlinetech.pojo.Course;
import com.freya.onlinetech.pojo.CourseRecord;
import com.freya.onlinetech.pojo.CourseVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseTagMapper courseTagMapper;
    @Autowired
    private TeacherInfoMapper teacherInfoMapper;
    @Autowired
    private SchoolMapper schoolMapper;

    public Course getCourseDetail(int id) {
        System.out.println("CourseService->getCourseDetail");
        return courseMapper.queryCourseById(id);
    }

    //查询课程的标签
    public List<String> getCourseTag(int id) {
        List<String> res = courseTagMapper.getCourseTag(id);
        System.out.println(res);
        return res;
    }

    //查询某个教师的最新课程
    public Course getLatestCourse(int teacher_id) {
        Course course = courseMapper.queryLatestCourse(teacher_id);
        return course;
    }

    //查询某个学生参与的课程
    public List<Course> getCoursesByStuID(int student_id) {
        List<Course> courses = courseMapper.getCoursesByStuID(student_id);
        for (int i = 0; i < courses.size(); i++) {
            //设置教师的名字
            int teacher_id = courses.get(i).getCourse_teacher_id();
            String teacher_name = teacherInfoMapper.getTeacherName(teacher_id);
            courses.get(i).setCourse_teacher_name(teacher_name);
            //设置学校的名字
            int school_id = courses.get(i).getCourse_school_id();
            String school_name = schoolMapper.getSchoolName(school_id);
            courses.get(i).setCourse_school_name(school_name);
            //得到该课程的视频数量
            int course_id = courses.get(i).getCourse_id();
            int section_num = courseMapper.getCourseSectionNum(course_id);
            courses.get(i).setCourse_section_num(section_num);
            //拿到学生参与课程的记录
            int wacthNum = getStuWatchVideoNum(student_id, course_id);
            courses.get(i).setCourse_watched_num(wacthNum);
            //学生观看课程完成的比率
            if (section_num == 0) {
                courses.get(i).setCourse_watch_ratio(100);
            } else {
                courses.get(i).setCourse_watch_ratio(wacthNum * 100 / section_num);
            }
        }
        return courses;
    }

    //查询学生参与课程的记录
    public CourseRecord getStuCourseRecord(int student_id, int course_id) {
        CourseRecord courseRecord = courseMapper.getStuCourseRecord(student_id, course_id);
        return courseRecord;
    }

    //查询学生参与某个课程观看的视频数量
    public int getStuWatchVideoNum(int student_id, int course_id) {
        int watchNum = courseMapper.getStuWatchVideoNum(student_id, course_id);
        return watchNum;
    }

    //查询课程详细的视频信息
    public CourseVideo queryCourseVideo(int course_id)
    {
        CourseVideo courseVideo = courseMapper.queryCourseVideo(course_id);
        return courseVideo;
    }

    //查询教师的全部课程
    public List<Course> queryAllTeacherCourse(int teacher_id)
    {
        List<Course> allCourse = courseMapper.queryAllTeacherCourse(teacher_id);
        return  allCourse;
    }

    //查询某个学生的推荐课程
    public List<Course> getRecommendCourse(int student_id)
    {
        List<Course> courses = courseMapper.getRecommendCourse(student_id);
        for (int i = 0; i < courses.size(); i++) {
            //设置教师的名字
            int teacher_id = courses.get(i).getCourse_teacher_id();
            String teacher_name = teacherInfoMapper.getTeacherName(teacher_id);
            courses.get(i).setCourse_teacher_name(teacher_name);
        }
        return  courses;
    }

    //查询某个课程的授课老师ID
    public int queryCourseTeacher(int course_id)
    {
        int teacher_id = courseMapper.queryCourseTeacher(course_id);
        return teacher_id;
    }
}

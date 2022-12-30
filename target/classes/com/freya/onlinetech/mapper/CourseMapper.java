package com.freya.onlinetech.mapper;

import com.freya.onlinetech.pojo.Course;
import com.freya.onlinetech.pojo.CourseRecord;
import com.freya.onlinetech.pojo.CourseVideo;
import com.freya.onlinetech.pojo.VideoInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;

import java.util.List;

@Mapper
@Controller
public interface CourseMapper {
    Course queryCourseById(int course_id);
    List<Course> getCoursesByStuID(int student_id);
    Course queryLatestCourse(int teacher_id);
    int addCourse(Course course);
    int updateCourse(Course course);
    int deleteCourse(int course_id);
    //得到该课程的视频数量
    int getCourseSectionNum(int course_id);
    //得到某个学生参与课程的情况
    CourseRecord getStuCourseRecord(int student_id, int course_id);
    //得到学生参与某个课程的情况（看过多少视频）
    int getStuWatchVideoNum(int student_id,int course_id);
    //查询课程的视频信息
    CourseVideo queryCourseVideo(int course_id);
    //查询老师的所有课程
    List<Course> queryAllTeacherCourse(int teacher_id);
    //查询某个课程的授课老师
    int queryCourseTeacher(int course_id);
    //查询某个学生的推荐课程
    List<Course> getRecommendCourse(int student_id);
}

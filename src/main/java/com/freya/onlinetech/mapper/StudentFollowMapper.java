package com.freya.onlinetech.mapper;

import com.freya.onlinetech.pojo.TeacherAndSchool;
import com.freya.onlinetech.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentFollowMapper {
    //得到学生关注的老师列表的前五名
    List<TeacherInfo> getStudentFollows(int student_id);
    //得到学生关注的全部老师的id
    List<Integer> getFollowedTeacherID(int student_id);
    //移除某个学生对老师的关注
    int studentFollowCancel(int student_id,int teacher_id);
    //添加某个学生对老师的关注
    int addStudentFollow(int student_id,int teacher_id);
    //得到学生关注的所有老师的列表
    List<TeacherInfo> getAllFollowedTeacher(int student_id);
    //得到推荐学生关注的列表
    List<TeacherInfo> getRecommendTeacher(int student_id);
    //得到所有的教师列表
    List<TeacherInfo> getAllTeachers();
}

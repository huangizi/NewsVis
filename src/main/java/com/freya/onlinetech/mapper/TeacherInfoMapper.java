package com.freya.onlinetech.mapper;

import com.freya.onlinetech.pojo.TeacherAndSchool;
import com.freya.onlinetech.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherInfoMapper {
    TeacherInfo getTeacherInfobyId(int teacher_id);
    //通过教师的ID得到教师的名字
    String getTeacherName(int teacher_id);
    //通过教师ID得到教师和学校的信息
    TeacherAndSchool getTeacherAndSchool(int teacher_id);
    //获取某个老师的全部授课课程
    List<Integer> getTeacherAllCourse(int teacher_id);
}

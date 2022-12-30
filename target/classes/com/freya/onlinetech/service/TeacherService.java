package com.freya.onlinetech.service;

import com.freya.onlinetech.mapper.TeacherInfoMapper;
import com.freya.onlinetech.pojo.TeacherAndSchool;
import com.freya.onlinetech.pojo.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherInfoMapper teacherInfoMapper;

    public TeacherInfo getTeacherInfobyId(int teacher_id)
    {
        TeacherInfo teacherInfo = teacherInfoMapper.getTeacherInfobyId(teacher_id);
        return teacherInfo;
    }

    public TeacherAndSchool getTeacherAndSchool(int teacher_id)
    {
        TeacherAndSchool teacherAndSchool = teacherInfoMapper.getTeacherAndSchool(teacher_id);
        return teacherAndSchool;
    }

    //获取某个教师的全部授课ID
    public List<Integer> getTeacherAllCourse(int teacher_id)
    {
        List<Integer> courses = teacherInfoMapper.getTeacherAllCourse(teacher_id);
        System.out.println("getTeacherAllCourse:"+courses);
        return courses;
    }
}

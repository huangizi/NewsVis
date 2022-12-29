package com.freya.onlinetech.service;

import com.freya.onlinetech.mapper.StudentFollowMapper;
import com.freya.onlinetech.mapper.TeacherInfoMapper;
import com.freya.onlinetech.pojo.TeacherAndSchool;
import com.freya.onlinetech.pojo.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentFollowService {
    @Autowired
    StudentFollowMapper studentFollowMapper;
    @Autowired
    TeacherInfoMapper teacherInfoMapper;

    //得到学生关注的老师的名字（前5个）
    public List<TeacherInfo> getStudentFollows(int student_id)
    {
        List<TeacherInfo> followTeachers = studentFollowMapper.getStudentFollows(student_id);
        return followTeachers;
    }

    //得到全部的教师
    public List<TeacherInfo> getAllTeachers()
    {
        List<TeacherInfo> followTeachers = studentFollowMapper.getAllTeachers();
        return followTeachers;
    }

    //得到所有老师的信息
    public List<TeacherAndSchool> getAllTeachersInfo()
    {
        List<TeacherInfo> studentFollow = studentFollowMapper.getAllTeachers();
        List<TeacherAndSchool> ans = new ArrayList<>();
        for (TeacherInfo teacherInfo : studentFollow) {
            TeacherAndSchool teacherAndSchool = teacherInfoMapper.getTeacherAndSchool(teacherInfo.getTeacher_id());
            ans.add(teacherAndSchool);
        }
        return ans;
    }

    //得到学生关注的老师的ID
    public List<Integer> getFollowedTeacherID(int student_id)
    {
        List<Integer> ans = studentFollowMapper.getFollowedTeacherID(student_id);
        return ans;
    }
    //移除某个学生对老师的关注
    public int studentFollowCancel(int student_id,int teacher_id)
    {
        int res = studentFollowMapper.studentFollowCancel(student_id,teacher_id);
        return res;
    }
    //添加某个学生对老师的关注
    public int addStudentFollow(int student_id,int teacher_id)
    {
        int res = studentFollowMapper.addStudentFollow(student_id,teacher_id);
        return res;
    }
    //得到学生关注的所有老师的列表
    public List<TeacherAndSchool> getAllFollowedTeacher(int student_id)
    {
        List<TeacherInfo> studentFollow = studentFollowMapper.getAllFollowedTeacher(student_id);
        List<TeacherAndSchool> ans = new ArrayList<>();
        for (TeacherInfo teacherInfo : studentFollow) {
            TeacherAndSchool teacherAndSchool = teacherInfoMapper.getTeacherAndSchool(teacherInfo.getTeacher_id());
            ans.add(teacherAndSchool);
        }
        return ans;
    }

    //得到该学生推荐的教师
    public List<TeacherAndSchool> getRecommendTeacher(int student_id)
    {
        List<TeacherInfo> recommendTeachers = studentFollowMapper.getRecommendTeacher(student_id);
        List<TeacherAndSchool> ans = new ArrayList<>();
        for (TeacherInfo teacherInfo : recommendTeachers) {
            TeacherAndSchool teacherAndSchool = teacherInfoMapper.getTeacherAndSchool(teacherInfo.getTeacher_id());
            ans.add(teacherAndSchool);
        }
        return ans;
    }
}

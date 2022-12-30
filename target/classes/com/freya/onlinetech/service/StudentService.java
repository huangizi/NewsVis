package com.freya.onlinetech.service;

import com.freya.onlinetech.mapper.StudentInfoMapper;
import com.freya.onlinetech.pojo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentInfoMapper studentInfoMapper;

    public StudentInfo getStudentById(int student_id)
    {
        StudentInfo studentInfo = studentInfoMapper.queryStudentById(student_id);
        return studentInfo;
    }

}

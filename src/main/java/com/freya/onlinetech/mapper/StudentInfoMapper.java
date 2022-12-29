package com.freya.onlinetech.mapper;

import com.freya.onlinetech.pojo.StudentInfo;
import com.freya.onlinetech.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentInfoMapper {
    StudentInfo queryStudentById(int id);
    int addStudent(StudentInfo studentInfo);
    StudentInfo queryStudentByName(String username);
    int updateStudent(StudentInfo studentInfo);
    int deleteStudent(int id);
}

package com.freya.onlinetech.service;

import com.freya.onlinetech.mapper.CommentsMapper;
import com.freya.onlinetech.mapper.TeacherInfoMapper;
import com.freya.onlinetech.pojo.CommentsRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private TeacherService teacherService;
    //获取某个课程的评论
    public List<CommentsRoot> getCommentsRoot(int course_id)
    {
        List<CommentsRoot> res = commentsMapper.getCommentsRoot(course_id);
        return res;
    }

    //获取某个教师的全部课程的评论
    public List<CommentsRoot> getTeacherComments(int teacher_id)
    {
        List<CommentsRoot> res = new ArrayList<>();
        //得到某个老师的全部课程
        List<Integer> courses = teacherService.getTeacherAllCourse(teacher_id);
        for (Integer course : courses) {
            List<CommentsRoot> temp = commentsMapper.getCommentsRoot(course);
//            for (CommentsRoot commentsRoot : temp) {
//                res.add(commentsRoot);
//            }
            res.addAll(temp);
        }
        System.out.println("getTeacherComments:"+res);
        return res;
    }
}

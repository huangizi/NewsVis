package com.freya.onlinetech.mapper;

import com.freya.onlinetech.pojo.VideoInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface VideoMapper {
    //得到该学生关注的教师的课程信息
    public List<VideoInfo> getFollowVideo(int student_id);
    //分页实现
    public List<VideoInfo> getTeacherVideo(int teacher_id);
    //得到教师视频总数的sql
    public int getTeacherVideoNum(int teacher_id);
    //得到教师的最新视频
    public List<VideoInfo> getLatestTeacherVideo(int teacher_id);
    //通过video_id得到视频的信息
    public VideoInfo getVideoById(int video_id);
    //得到教师最新课程的第一节视频信息
    public VideoInfo queryLatestCourseVideo(int course_id);
}

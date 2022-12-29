package com.freya.onlinetech.service;

import com.freya.onlinetech.mapper.VideoMapper;
import com.freya.onlinetech.pojo.VideoInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    VideoMapper videoMapper;

    //得到用户关注的教师的视频
    public List<VideoInfo> getFollowVideo(int student_id) {
        List<VideoInfo> FollowVideos = videoMapper.getFollowVideo(student_id);
        return FollowVideos;
    }

    //得到教师的视频，并且分页展示
    public List<VideoInfo> getTeacherVideo(int teacher_id, int pageNum, int pageSize) {
        //使用pageHelper实现分页
        PageHelper.startPage(pageNum, pageSize);  //当前是pageNum页,一页显示几个
        List<VideoInfo> list = videoMapper.getTeacherVideo(teacher_id);
        PageInfo<VideoInfo> pi = new PageInfo<>(list);
        return pi.getList();
    }

    //得到教师的全部视频个数
    public int getTeacherVideoNum(int teacher_id) {
        int res = videoMapper.getTeacherVideoNum(teacher_id);
        return res;
    }

    //得到最新的视频
    public List<VideoInfo> getLatestTeacherVideo(int teacher_id) {
        List<VideoInfo> list = videoMapper.getLatestTeacherVideo(teacher_id);
        return list;
    }

    //通过视频Id得到视频
    public VideoInfo getVideoById(int video_id)
    {
        VideoInfo videoInfo = videoMapper.getVideoById(video_id);
        return videoInfo;
    }

    public VideoInfo queryLatestCourseVideo(int course_id) {
        VideoInfo videoInfo = videoMapper.queryLatestCourseVideo(course_id);
        return videoInfo;
    }
}

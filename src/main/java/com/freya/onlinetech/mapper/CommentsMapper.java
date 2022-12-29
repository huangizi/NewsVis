package com.freya.onlinetech.mapper;

import com.freya.onlinetech.pojo.CommentsRoot;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentsMapper {
    //获取父评论(video_id是视频的id)
    List<CommentsRoot> getCommentsRoot(int video_id);
}

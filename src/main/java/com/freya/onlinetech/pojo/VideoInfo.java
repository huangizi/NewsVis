package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoInfo {
    private int video_id;
    private int video_course_id;
    private int video_chapter_id;
    private int video_section_id;
    private Date video_add_time;
    private int video_times;
    private int video_view_num;
    private String video_image;
    private int video_teacher_id;
    private String video_name;
    private String teacher_name;
    private int video_good_num;
    private int video_bad_num;
    //视频的链接
    private String video_href;
}

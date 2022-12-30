package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAndSchool {
    private int teacher_id;
    private String teacher_name;
    private int teacher_school_id;
    private int teacher_view_num;
    private int teacher_follow_num;
    private int teacher_video_num;
    private Date teacher_join_time;
    //从学校数据表中获得
    private String school_name;
    private String school_introduction;
    private String school_img_name_href;
    private String school_logo_href;
}

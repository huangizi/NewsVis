package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherInfo {
    private int teacher_id;
    private String teacher_name;
    private int teacher_school_id;
    private int teacher_view_num;
    private int teacher_follow_num;
    private int teacher_video_num;
    private Date teacher_join_time;
}

package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int course_id;
    private String course_name;
    private int course_teacher_id;
    private String course_description;
    private String course_category;
    private String course_degree;
    private int course_times;
    private int course_fav_nums;
    private int course_view_num;
    private Date course_add_time;
    private int course_school_id;
    private Date course_end_time;
    //从其他表并联查询过来的
    private String course_teacher_name;
    private String course_school_name;
    private int course_section_num;  //一共有多少节
    private int course_watched_num; //学生该课程看了多少
    private int course_watch_ratio; //学生观看课程完成的比率
}

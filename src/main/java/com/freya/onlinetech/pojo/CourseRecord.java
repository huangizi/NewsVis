package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRecord {
    private int course_record_student_id;
    private int course_record_teacher_id;
    private int course_record_course_id;
    //级联查询出来的值
    private  int course_record_num;
}

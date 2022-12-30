package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseVideo {
    private int course_id;
    //该课程的章的信息
    private List<CourseChapter> chapter;
}

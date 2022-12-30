package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseChapter {
    private int course_chapter_id;
    private int course_section_num;
    private String course_chapter_name;
    //该章的节的信息
    List<CourseSection> section;
}

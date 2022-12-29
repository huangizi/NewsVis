package com.freya.onlinetech.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseTagMapper {
    List<String> getCourseTag(int course_id);
}

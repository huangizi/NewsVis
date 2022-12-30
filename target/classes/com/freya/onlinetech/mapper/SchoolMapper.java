package com.freya.onlinetech.mapper;

import com.freya.onlinetech.pojo.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;

import java.util.List;

@Mapper
@Controller
public interface SchoolMapper {
    //得到学校的名字
    public String getSchoolName(int school_id);
    //得到学校的所有信息
    public School getSchoolInfo(int school_id);
    //得到五个学校进行推荐
    public List<School> getSchoolLim5();
    //得到数据库里所有的学校
    public List<School> getAllSchools();
}

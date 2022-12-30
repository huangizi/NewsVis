package com.freya.onlinetech.service;

import com.freya.onlinetech.mapper.SchoolMapper;
import com.freya.onlinetech.pojo.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    @Autowired
    SchoolMapper schoolMapper;

    public School getSchoolInfo(int school_id)
    {
        School school = schoolMapper.getSchoolInfo(school_id);
        return school;
    }

    public List<School> getSchoolLim5()
    {
        List<School> schools = schoolMapper.getSchoolLim5();
        return schools;
    }

    public List<School> getAllSchools()
    {
        List<School> schools = schoolMapper.getAllSchools();
        return schools;
    }
}

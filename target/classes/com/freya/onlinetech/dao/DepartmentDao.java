package com.freya.onlinetech.dao;

import com.freya.onlinetech.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//部门dao
@Repository
public class DepartmentDao {
    //模拟数据库中的数据
    private static Map<Integer, Department> departments = null;
    static {
        departments = new HashMap<Integer,Department>();
        departments.put(101,new Department(101,"教学部"));
        departments.put(102,new Department(102,"教研部"));
        departments.put(103,new Department(103,"市场部"));
    }
    //获得所有部门信息
    public Collection<Department> getDepartments(){
        return departments.values();
    }

    //通过id得到部门
    public Department getDepartmentById(Integer id)
    {
        return departments.get(id);
    }
}

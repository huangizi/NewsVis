package com.freya.onlinetech.dao;

import com.freya.onlinetech.pojo.Department;
import com.freya.onlinetech.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//员工Dao
@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees = new HashMap<Integer,Employee>();
        employees.put(1001,new Employee(1001,"AA","123456@qq.com",0,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"BB","123456@qq.com",1,new Department(101,"教学部")));
        employees.put(1003,new Employee(1003,"CC","123456@qq.com",0,new Department(101,"教学部")));
    }

    //主键自增
    private static Integer initId = 1006;

    //增加一个员工
    public void save(Employee employee)
    {
        if(employee.getId() == null)
        {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }
    //获得所有员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过id得到员工
    public Employee getEmployeeById(Integer id)
    {
        return employees.get(id);
    }

    //删除员工
    public void deleteEmpById(int id)
    {
        employees.remove(id);
    }
}

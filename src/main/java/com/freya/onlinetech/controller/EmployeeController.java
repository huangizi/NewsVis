package com.freya.onlinetech.controller;

import com.freya.onlinetech.dao.DepartmentDao;
import com.freya.onlinetech.dao.EmployeeDao;
import com.freya.onlinetech.pojo.Department;
import com.freya.onlinetech.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }



    @GetMapping("/emp")
    public String toAddpage(Model model){
        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
         return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee)
    {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 修改员工，回显员工表
    @GetMapping("/emp/{id}")
    public String toUpdateEmpPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    // 修改员工
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //删除员工
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") int id) {
        employeeDao.deleteEmpById(id);
        return "redirect:/emps";
    }
}

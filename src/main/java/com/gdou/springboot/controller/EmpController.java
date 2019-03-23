package com.gdou.springboot.controller;

import com.gdou.springboot.dao.DepartmentDao;
import com.gdou.springboot.dao.EmployeeDao;
import com.gdou.springboot.entities.Department;
import com.gdou.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;

@Controller
public class EmpController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面,查出所有的部门供页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    //SpringMVC自动封装参数
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);//保存员工信息
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable Integer id, Model model){
        Employee employee = employeeDao.get(id);
        //来到添加页面,查出所有的部门供页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        model.addAttribute("emp",employee);
        //回到修改页面
        return "emp/add";
    }
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
         return "redirect:/emps";
    }
}

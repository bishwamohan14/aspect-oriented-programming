package com.codeDecode.aopDemo.service;

import com.codeDecode.aopDemo.entity.Employee;
import com.codeDecode.aopDemo.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public List<Employee> getAllEmps() {
        return employeeRepo.findAll();
    }

    public Employee addEmployee(Employee employee) throws Exception {
        if (employee.getName().length()>5){
            throw new Exception("Sorry please reduce size of your name");
        }
        return employeeRepo.save(employee);
    }
}

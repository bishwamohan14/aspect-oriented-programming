package com.codeDecode.aopDemo.controller;

import com.codeDecode.aopDemo.entity.Employee;
import com.codeDecode.aopDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/code")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<?> fetchAllEmployee(){
        return  new ResponseEntity<List<Employee>>(employeeService.getAllEmps(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) throws Exception {
        return  new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.OK);
    }





}

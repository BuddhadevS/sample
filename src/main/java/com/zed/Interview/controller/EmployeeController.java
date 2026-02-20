package com.zed.Interview.controller;

import com.zed.Interview.dto.EmployeeDto;
import com.zed.Interview.model.Employee;
import com.zed.Interview.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public Employee saveData(@Valid @RequestBody EmployeeDto employeeDto){
        return employeeService.saveData(employeeDto);
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.finadAllData();
    }

    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable Integer id){
        return employeeService.findById(id);
    }


}
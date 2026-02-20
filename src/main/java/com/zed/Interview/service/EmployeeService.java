package com.zed.Interview.service;

import com.zed.Interview.dto.EmployeeDto;
import com.zed.Interview.model.Employee;
import com.zed.Interview.repository.EmployeeRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee saveData(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());

        return employeeRepo.save(employee);
    }

    public List<Employee> finadAllData() {
        return employeeRepo.findAll();
    }

    public Employee findById(Integer id) {
        return employeeRepo.findById(id).orElseThrow(()->new RuntimeException("It not found "+id));
    }
}
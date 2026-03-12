package com.zed.Interview.service;

import com.zed.Interview.dto.EmployeeDto;
import com.zed.Interview.exception.InvalidEmailException;
import com.zed.Interview.exception.InvalidSalaryException;
import com.zed.Interview.model.Employee;
import com.zed.Interview.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee saveData(EmployeeDto employeeDto) {
        if (employeeDto.getSalary() == null || employeeDto.getSalary() < 1) {
            throw new InvalidSalaryException("Salary must be greater than 0");
        }
        if (employeeDto.getEmail() == null || !employeeDto.getEmail().contains("@")) {
            throw new InvalidEmailException("Invalid email format");
        }

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




    public Employee updateEmployee(Integer id, EmployeeDto employeeDto) {

        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));

        if (employeeDto.getSalary() == null || employeeDto.getSalary() < 1) {
            throw new InvalidSalaryException("Salary must be greater than 0");
        }

        if (employeeDto.getEmail() == null || !employeeDto.getEmail().contains("@")) {
            throw new InvalidEmailException("Invalid email format");
        }

        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());

        return employeeRepo.save(employee);
    }

    public Employee patchEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));

        if (employeeDto.getName() != null) {
            employee.setName(employeeDto.getName());
        }
        if (employeeDto.getEmail() != null) {
            if (!employeeDto.getEmail().contains("@")) {
                throw new InvalidEmailException("Invalid email format");
            }
            employee.setEmail(employeeDto.getEmail());
        }
        if (employeeDto.getDepartment() != null) {
            employee.setDepartment(employeeDto.getDepartment());
        }
        if (employeeDto.getSalary() != null) {
            if (employeeDto.getSalary() < 1) {
                throw new InvalidSalaryException("Salary must be greater than 0");
            }
            employee.setSalary(employeeDto.getSalary());
        }

        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employeeRepo.delete(employee);
    }
}

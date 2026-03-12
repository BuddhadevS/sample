package com.zed.Interview.service;

import com.zed.Interview.dto.EmployeeDto;
import com.zed.Interview.exception.InvalidEmailException;
import com.zed.Interview.exception.InvalidSalaryException;
import com.zed.Interview.exception.ResourceNotFoundException;
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
        validateEmployee(employeeDto);
        return employeeRepo.save(mapToEmployee(new Employee(), employeeDto));
    }

    public List<Employee> finadAllData() {
        return employeeRepo.findAll();
    }

    public Employee findById(Integer id) {
        return getEmployeeById(id);
    }

    public Employee updateEmployee(Integer id, EmployeeDto employeeDto) {
        validateEmployee(employeeDto);
        Employee employee = getEmployeeById(id);
        return employeeRepo.save(mapToEmployee(employee, employeeDto));
    }

    public Employee patchEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employee = getEmployeeById(id);

        if (employeeDto.getName() != null) {
            employee.setName(employeeDto.getName());
        }
        if (employeeDto.getEmail() != null) {
            validateEmail(employeeDto.getEmail());
            employee.setEmail(employeeDto.getEmail());
        }
        if (employeeDto.getDepartment() != null) {
            employee.setDepartment(employeeDto.getDepartment());
        }
        if (employeeDto.getSalary() != null) {
            validateSalary(employeeDto.getSalary());
            employee.setSalary(employeeDto.getSalary());
        }

        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepo.delete(getEmployeeById(id));
    }

    private Employee getEmployeeById(Integer id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    private void validateEmployee(EmployeeDto employeeDto) {
        validateSalary(employeeDto.getSalary());
        validateEmail(employeeDto.getEmail());
    }

    private void validateSalary(Long salary) {
        if (salary == null || salary < 1) {
            throw new InvalidSalaryException("Salary must be greater than 0");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new InvalidEmailException("Invalid email format");
        }
    }

    private Employee mapToEmployee(Employee employee, EmployeeDto employeeDto) {
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());
        return employee;
    }
}

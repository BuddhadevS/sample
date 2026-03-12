package com.zed.Interview.service;

import com.zed.Interview.dto.EmployeeDto;
import com.zed.Interview.exception.InvalidEmailException;
import com.zed.Interview.exception.InvalidSalaryException;
import com.zed.Interview.exception.ResourceNotFoundException;
import com.zed.Interview.model.Employee;
import com.zed.Interview.repository.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void saveDataShouldPersistValidatedEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Asha");
        employeeDto.setEmail("asha@example.com");
        employeeDto.setDepartment("Engineering");
        employeeDto.setSalary(50000L);

        Employee savedEmployee = new Employee();
        savedEmployee.setId(1);
        savedEmployee.setName(employeeDto.getName());
        savedEmployee.setEmail(employeeDto.getEmail());
        savedEmployee.setDepartment(employeeDto.getDepartment());
        savedEmployee.setSalary(employeeDto.getSalary());

        when(employeeRepo.save(any(Employee.class))).thenReturn(savedEmployee);

        Employee result = employeeService.saveData(employeeDto);

        assertEquals("Asha", result.getName());
        assertEquals("asha@example.com", result.getEmail());
        assertEquals("Engineering", result.getDepartment());
        assertEquals(50000L, result.getSalary());
        verify(employeeRepo).save(any(Employee.class));
    }

    @Test
    void saveDataShouldRejectInvalidEmail() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Asha");
        employeeDto.setEmail("invalid-email");
        employeeDto.setSalary(50000L);

        assertThrows(InvalidEmailException.class, () -> employeeService.saveData(employeeDto));
        verify(employeeRepo, never()).save(any(Employee.class));
    }

    @Test
    void patchEmployeeShouldRejectInvalidSalary() {
        Employee existingEmployee = new Employee();
        existingEmployee.setId(7);
        existingEmployee.setName("Ravi");
        existingEmployee.setEmail("ravi@example.com");
        existingEmployee.setSalary(30000L);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setSalary(0L);

        when(employeeRepo.findById(7)).thenReturn(Optional.of(existingEmployee));

        assertThrows(InvalidSalaryException.class, () -> employeeService.patchEmployee(7, employeeDto));
        verify(employeeRepo, never()).save(any(Employee.class));
    }

    @Test
    void findByIdShouldThrowWhenEmployeeMissing() {
        when(employeeRepo.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.findById(99));
    }
}

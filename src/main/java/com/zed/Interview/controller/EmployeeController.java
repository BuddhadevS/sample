package com.zed.Interview.controller;

import com.zed.Interview.dto.EmployeeDto;
import com.zed.Interview.model.Employee;
import com.zed.Interview.resp.ApiResponse;
import com.zed.Interview.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<Employee>> saveData(@Valid @RequestBody EmployeeDto employeeDto){

        Employee employee = employeeService.saveData(employeeDto);

        ApiResponse<Employee> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Employee created successfully",
                employee,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<ApiResponse<List<Employee>>> findAll(){

        List<Employee> list = employeeService.finadAllData();

        ApiResponse<List<Employee>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employee list fetched successfully",
                list,
                null
        );

        return ResponseEntity.ok(response);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> getById(@PathVariable Integer id){

        Employee employee = employeeService.findById(id);

        ApiResponse<Employee> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employee fetched successfully",
                employee,
                null
        );

        return ResponseEntity.ok(response);
    }


    @PutMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(
            @PathVariable Integer id,
            @Valid @RequestBody EmployeeDto employeeDto) {

        Employee employee = employeeService.updateEmployee(id, employeeDto);


        ApiResponse<Employee> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employee updated successfully",
                employee,
                null
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> patchEmployee(
            @PathVariable Integer id,
            @RequestBody EmployeeDto employeeDto) {

        Employee employee = employeeService.patchEmployee(id, employeeDto);

        ApiResponse<Employee> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employee updated successfully",
                employee,
                null
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);

        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Employee deleted successfully",
                null,
                null
        );

        return ResponseEntity.ok(response);
    }

}

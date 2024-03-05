package com.kt.edu.thirdproject.command.controller;

import com.kt.edu.thirdproject.command.repository.entity.Employee;
import com.kt.edu.thirdproject.command.service.EmployeeCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Employee", description = "Employee API")
@RestController
//@RequiredArgsConstructor
@CrossOrigin(origins ="*")
@RequestMapping("/api/v1/")
public class EmployeeCommandController {
    @Autowired
    private EmployeeCommandService employeeCommandService;

    @PostMapping("/employees")
    @Operation(summary ="임직원 등록",description="임직원을 등록합니다.")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee createdEmployee = employeeCommandService.create(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    @PostMapping("/employees/{id}")
    @Operation(summary ="임직원 수정",description="임직원 정보를  수정합니다.")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeCommandService.update(id,employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PostMapping("/employee/{id}")
    @Operation(summary ="임직원 정보 삭제",description="임직원 정보를 삭제합니다.")
    public ResponseEntity<Employee> delete(@PathVariable Long id) {
        Employee deletedEmployee = employeeCommandService.delete(id);
        return ResponseEntity.ok(deletedEmployee);
    }
}

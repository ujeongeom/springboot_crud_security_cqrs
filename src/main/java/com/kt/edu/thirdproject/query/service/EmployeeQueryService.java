package com.kt.edu.thirdproject.query.service;

import com.kt.edu.thirdproject.query.repository.EmployeeQueryRepository;
import com.kt.edu.thirdproject.query.repository.entity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeQueryService {

    private final EmployeeQueryRepository employeeQueryRepository;

    // jasypt로 저장된 비밀번호가  복호화 된다.
    @Value("${spring.datasource.password}")
    private String h2Password;

    public List<Employee> employee(long id) {
        return employeeQueryRepository.findById(id);
    }

    public List<Employee> getEmployeeList() {
        log.info("Request to get all Employees");
        log.info("h2 password : " + h2Password );
        List<Employee> employeeList = new ArrayList<>();
        employeeQueryRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    @Transactional(readOnly = true)
    public Employee getEmployee(Long id) {
        log.info("Request to get Employee : {}", id);
        return employeeQueryRepository.findById(id).get();
    }


}

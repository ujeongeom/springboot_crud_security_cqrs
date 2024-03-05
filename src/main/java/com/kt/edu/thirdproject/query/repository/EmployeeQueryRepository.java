package com.kt.edu.thirdproject.query.repository;

import com.kt.edu.thirdproject.query.repository.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeQueryRepository extends CrudRepository<Employee, Long> {
    List<Employee> findById(long id);
}

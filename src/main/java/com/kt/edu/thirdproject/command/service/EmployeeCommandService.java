package com.kt.edu.thirdproject.command.service;

import com.kt.edu.thirdproject.command.repository.EmployeeCommandRepository;
import com.kt.edu.thirdproject.command.repository.entity.Employee;
import com.kt.edu.thirdproject.common.annotation.Ktedu;
import com.kt.edu.thirdproject.common.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
@Slf4j
public class EmployeeCommandService {
    @Autowired
    private EmployeeCommandRepository employeeCommandRepository;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${spring.datasource.password}")
    private String h2Password;

    @Transactional
    public Employee create(Employee employee) {
        log.info("Request to create Employee : " +  employee);
        log.info("Active Springboot Profile : " + activeProfile );

        Long newId = (activeProfile.equals("prd")) ?
                employeeCommandRepository.retvNextVal_C() :
                employeeCommandRepository.retvNextVal_C_H2();

        // setId 호출 전에 newId가 null이 아닌지 확인
        if (newId != null) {
            employee.setId(newId);
            employee.setNew(true);

            return this.employeeCommandRepository.save(
                    Employee.newEmployee(
                            employee.getId(),
                            employee.getEmpName(),
                            employee.getEmpDeptName(),
                            employee.getEmpTelNo(),
                            employee.getEmpMail()
                    )
            );
        } else {
            // newId가 null이면 적절한 처리를 수행
            log.error("Failed to retrieve a new ID.");
            // 예외 처리 등을 추가하거나, 다른 대응을 취할 수 있습니다.
            return null;
        }
    }

    @Ktedu
    public Employee update(Long id, Employee employee) {
        log.info("Request to update Employee : " +  employee);
        Employee employeeEntity = employeeCommandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        employeeEntity.setId(id);
        employeeEntity.setEmpName(employee.getEmpName());
        employeeEntity.setEmpDeptName(employee.getEmpDeptName());
        employeeEntity.setEmpTelNo(employee.getEmpTelNo());
        employeeEntity.setEmpMail(employee.getEmpMail());
        employeeEntity.setNew(false);
        return this.employeeCommandRepository.save(employeeEntity);
    }

    public Employee delete(Long id) {
        log.info("Request to delete Employee id : " +  id);
        Employee employee = employeeCommandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not exist with id" +id));
        employeeCommandRepository.delete(employee);
        return  employee;
    }
}

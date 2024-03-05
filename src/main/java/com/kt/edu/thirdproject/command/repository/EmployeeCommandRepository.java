package com.kt.edu.thirdproject.command.repository;

import com.kt.edu.thirdproject.command.repository.entity.Employee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeCommandRepository extends CrudRepository<Employee, Long> {

    //profile is prd ( maria, mysql )
    String RETV_NEXT_VAL_C = """
    SELECT NEXTVAL(hibernate_sequence);
    """;

    //profile is local,dev ( h2 )
    String RETV_NEXT_VAL_C_H2 = """
    SELECT NEXTVAL('hibernate_sequence');
    """;

    @Query(RETV_NEXT_VAL_C)
    Long retvNextVal_C();

    @Query(RETV_NEXT_VAL_C_H2)
    Long retvNextVal_C_H2();
}

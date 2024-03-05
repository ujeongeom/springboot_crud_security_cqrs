package com.kt.edu.thirdproject.command.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("EMPLOYEE")
@Schema(description = "Employee Command Entity")
//json 에서 해당 값 제외
@JsonIgnoreProperties({"new"})
public class Employee implements Persistable<Long> {

    @Transient
    private boolean isNew = false;

    @Id
    @Schema(description = "순서")
    private Long id;

    @Schema(description = "직원명")
    @Column("EMPNAME")
    private String empName;

    @Schema(description = "부서명")
    @Column("EMPDEPTNAME")
    private String empDeptName;

    @Schema(description = "전화번호")
    @Column("EMPTELNO")
    private String empTelNo;

    @Schema(description = "이메일")
    @Column("EMPMAIL")
    private String empMail;

    public static Employee newEmployee(long id, String empName, String empDeptName, String empTelNo, String empMail){
        Employee employee = new Employee(true, id, empName, empDeptName, empTelNo, empMail);
        return employee;
    }

    @Override
    public boolean isNew() { return isNew; }

}

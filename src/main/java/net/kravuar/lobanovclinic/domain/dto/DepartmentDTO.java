package net.kravuar.lobanovclinic.domain.dto;

import lombok.*;
import net.kravuar.lobanovclinic.domain.model.clinic.Department;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDTO {
    private long departmentId;
    private String name;

    public DepartmentDTO(Department department) {
        this.departmentId = department.getId();
        this.name = department.getName();
    }
}
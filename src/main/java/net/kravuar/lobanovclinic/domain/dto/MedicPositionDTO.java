package net.kravuar.lobanovclinic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.MedicPosition;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicPositionDTO {
    private DepartmentDTO department;
    private PositionDTO position;

    public MedicPositionDTO(MedicPosition medicPosition) {
        this.department = new DepartmentDTO(medicPosition.getDepartment());
        this.position = new PositionDTO(medicPosition.getPosition());
    }
}
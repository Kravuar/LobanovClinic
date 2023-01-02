package net.kravuar.lobanovclinic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.users.Patient;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDTO {
    private HumanDTO patient;
    private DepartmentDTO department;
    private int ward;

    public PatientDTO(Patient patient) {
        this.patient = new HumanDTO(patient.getHuman());
        this.department = new DepartmentDTO(patient.getDepartment());
        this.ward = patient.getWard();
    }
}
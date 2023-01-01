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
public class PatientFullDTO extends PatientDTO{
    private HumanDTO medic;

    public PatientFullDTO(Patient patient) {
        super(patient);
        this.medic = new HumanDTO(patient.getMedic().getHuman());
    }
}
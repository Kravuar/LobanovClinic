package net.kravuar.lobanovclinic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.users.Medic;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicDTO {
    private HumanDTO medic;

    public MedicDTO(Medic medic) {
        this.medic = new HumanDTO(medic.getHuman());
    }
}
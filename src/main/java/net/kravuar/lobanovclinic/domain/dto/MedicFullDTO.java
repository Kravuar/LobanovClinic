package net.kravuar.lobanovclinic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.users.Medic;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicFullDTO extends MedicDTO {
    private Set<MedicPositionDTO> positions;

    public MedicFullDTO(Medic medic) {
        super(medic);
        this.positions = medic.getPositions().stream()
                .map(MedicPositionDTO::new)
                .collect(Collectors.toSet());
    }
}
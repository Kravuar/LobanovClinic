package net.kravuar.lobanovclinic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.Department;
import net.kravuar.lobanovclinic.domain.model.clinic.Position;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PositionDTO {
    private String name;

    public PositionDTO(Position position) {
        this.name = position.getName();
    }
}
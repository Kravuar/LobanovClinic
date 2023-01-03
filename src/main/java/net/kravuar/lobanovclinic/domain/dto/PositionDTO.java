package net.kravuar.lobanovclinic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.Position;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PositionDTO {
    private long id;
    private String name;
    private float salary;
    public PositionDTO(Position position) {
        this.id = position.getId();
        this.name = position.getName();
        this.salary = position.getSalary();
    }
}
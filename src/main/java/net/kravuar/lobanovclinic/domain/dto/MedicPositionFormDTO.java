package net.kravuar.lobanovclinic.domain.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicPositionFormDTO {
    @Min(value = 0, message = "Номер отделения не может быть отрицательным.")
    private Long departmentId;

    @Min(value = 0, message = "Номер должности не может быть отрицательным.")
    private Long positionId;
}
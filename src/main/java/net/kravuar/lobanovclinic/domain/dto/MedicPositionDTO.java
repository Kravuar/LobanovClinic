package net.kravuar.lobanovclinic.domain.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MedicPositionDTO {
    @Min(value = 0, message = "Номер отделения не может быть отрицательным.")
    private final Long departmentId;

    @Min(value = 0, message = "Номер должности не может быть отрицательным.")
    private final Long positionId;
}

package net.kravuar.lobanovclinic.domain.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PatientFormDTO {
    @Min(value = 0, message = "Номер паспорта пациента не может быть отрицательным.")
    private final long patientPassport;

    @Min(value = 0, message = "Номер паспорта врача не может быть отрицательным.")
    private final long medicPassport;

    @Min(value = 0, message = "Номер отделения не может быть отрицательным.")
    private final long departmentId;

    @Min(value = 0, message = "Номер палаты не может быть отрицательным.")
    private final int ward;
}
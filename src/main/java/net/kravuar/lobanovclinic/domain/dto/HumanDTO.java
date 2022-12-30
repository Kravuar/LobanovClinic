package net.kravuar.lobanovclinic.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class HumanDTO {
    @Size(min = 5, max = 40, message = "ФИО должно быть от 5 до 40 символов.")
    private String fullName;

    private Date dateOfBirth;
}

package net.kravuar.lobanovclinic.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class MedicFormDTO {
    @Min(value = 0, message = "Номер паспорта не может быть отрицательным.")
    private final Long passport;

    @Size(min = 5, max = 20, message = "Имя пользователя должно быть от 5 до 20 символов.")
    private final String username;

    @Size(min = 5, message = "Пароль должно содержать хотя бы 5 символов.")
    private final String password;

    private final Set<@Size(min = 3, message = "Название должности должно быть от 5 до 30 символов.") String> positions;
}
package net.kravuar.lobanovclinic.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.users.Human;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HumanDTO {
    @Min(value = 0, message = "Номер паспорта не может быть отрицательным.")
    private Long passport;

    @Size(min = 5, max = 40, message = "ФИО должно быть от 5 до 40 символов.")
    private String fullName;

    private Date dateOfBirth;

    public HumanDTO(Human human) {
        this.passport = human.getPassport();
        this.fullName = human.getFullName();
        this.dateOfBirth = human.getDateOfBirth();
    }
}

package net.kravuar.lobanovclinic.domain.model.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "humans")
public class Human {
    @Id
    @Min(value = 0, message = "Номер паспорта не может быть отрицательным.")
    private Long passport;

    @Column(unique = true, nullable = false)
    @Size(min = 5, max = 40, message = "ФИО должно быть от 5 до 40 символов.")
    private String fullName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return fullName.equals(human.fullName) && dateOfBirth.equals(human.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, dateOfBirth);
    }
}

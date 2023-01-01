package net.kravuar.lobanovclinic.domain.model.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.Department;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "patients")
public class Patient {
    @Id
    @Min(value = 0, message = "Номер паспорта не может быть отрицательным.")
    private Long passport;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Human human;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Human medic;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Department department;

    @Min(value = 0, message = "Номер палаты не может быть отрицательным.")
    private int ward;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return passport.equals(patient.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
package net.kravuar.lobanovclinic.domain.model.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.MedicPosition;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "medics")
public class Medic {
    @Id
    @Min(value = 0, message = "Номер паспорта не может быть отрицательным.")
    private Long passport;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Human human;

    @Column(unique = true)
    @Size(min = 5, max = 20 , message = "Имя пользователя должно быть от 5 до 20 символов.")
    private String username;

    @Column(unique = true)
    @Size(min = 5, max = 15 , message = "Номер телефона должен быть от 5 до 20 символов.")
    private String phoneNumber;

    @Column(nullable = false)
    @Size(max = 70, message = "Хэш пароля не должен быть больше 70 символов.")
    private String password;

    @OneToMany(mappedBy = "medic",orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MedicPosition> positions = new HashSet<>();

    @OneToMany(mappedBy = "medic", fetch = FetchType.LAZY)
    private Set<Patient> patients = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medic medic = (Medic) o;
        return username.equals(medic.username);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
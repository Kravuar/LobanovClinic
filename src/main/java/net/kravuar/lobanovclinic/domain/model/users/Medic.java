package net.kravuar.lobanovclinic.domain.model.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Column(nullable = false)
    @Size(max = 70, message = "Хэш пароля не должен быть больше 70 символов.")
    private String password;

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

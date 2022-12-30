package net.kravuar.lobanovclinic.domain.model.clinic;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Getter
@Setter
@Table(name = "positions")
public class Position implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 5, max = 30, message = "Название должности должно быть от 5 до 30 символов.")
    private String name;

    @Column(nullable = false)
    @Min(value = 0, message = "Ранг должности не может быть отрицательным.")
    private int rank;

    @Column(nullable = false)
    @Min(value = 0, message = "Зарплата не может быть отрицательной.")
    private float salary;

    @Override
    public String getAuthority() {
        return name;
    }
}

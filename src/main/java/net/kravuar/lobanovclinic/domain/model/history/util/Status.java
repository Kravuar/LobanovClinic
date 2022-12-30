package net.kravuar.lobanovclinic.domain.model.history.util;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min = 5, max = 30, message = "Название статуса должно быть от 5 до 30 символов.")
    private String name;
}

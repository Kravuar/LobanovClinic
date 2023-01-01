package net.kravuar.lobanovclinic.domain.model.clinic;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 5, max = 30, message = "Название услуги должно быть от 5 до 30 символов.")
    private String name;

    @Column(unique = true, nullable = false)
    @Size(max = 512, message = "Описание услуги должно быть не более 512 символов.")
    private String description;
}

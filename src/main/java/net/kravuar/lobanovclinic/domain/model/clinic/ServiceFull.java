package net.kravuar.lobanovclinic.domain.model.clinic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "services_with_prices")
public class ServiceFull {
    @Id
    private Long service_id;

    @Column(nullable = false)
    @Size(min = 5, max = 30, message = "Название услуги должно быть от 5 до 30 символов.")
    private String name;

    @Column(unique = true, nullable = false)
    @Size(max = 512, message = "Описание услуги должно быть не более 512 символов.")
    private String description;

    @Column(nullable = false)
    private float price;
}
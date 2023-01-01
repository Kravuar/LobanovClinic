package net.kravuar.lobanovclinic.domain.model.clinic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "services_with_prices")
public class ServiceFull extends Service{
    @Column(nullable = false)
    private float price;
}
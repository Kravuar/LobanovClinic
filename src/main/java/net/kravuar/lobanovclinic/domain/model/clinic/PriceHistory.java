package net.kravuar.lobanovclinic.domain.model.clinic;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.util.ServiceTimestampKey;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "price_history")
public class PriceHistory {
    @EmbeddedId
    private ServiceTimestampKey key;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("serviceId")
    private Service service;

    @Column(nullable = false)
    @Min(value = 0, message = "Цена не может быть отрицательной.")
    private float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceHistory that = (PriceHistory) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
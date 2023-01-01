package net.kravuar.lobanovclinic.domain.model.clinic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.util.MedicPositionKey;
import net.kravuar.lobanovclinic.domain.model.users.Human;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "medic_positions")
public class MedicPositions {
    @EmbeddedId
    private MedicPositionKey key;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("medic_passport")
    private Human medic;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("department")
    private Department department;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("position")
    private Position position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicPositions that = (MedicPositions) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
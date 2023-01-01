package net.kravuar.lobanovclinic.domain.model.clinic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.util.MedicPositionKey;
import net.kravuar.lobanovclinic.domain.model.users.Human;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "medic_positions")
public class MedicPosition {
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

    public MedicPosition(Human medic, Department department, Position position) {
        this.key = new MedicPositionKey(medic.getPassport(), position.getId(), department.getId());
        this.medic = medic;
        this.department = department;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicPosition that = (MedicPosition) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
package net.kravuar.lobanovclinic.domain.model.history;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.Department;
import net.kravuar.lobanovclinic.domain.model.clinic.Position;
import net.kravuar.lobanovclinic.domain.model.history.util.HumanTimestampKey;
import net.kravuar.lobanovclinic.domain.model.history.util.Status;
import net.kravuar.lobanovclinic.domain.model.users.Human;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "position_history")
public class PositionHistory {
    @EmbeddedId
    private HumanTimestampKey key;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("humanId")
    private Human medic;

    @ManyToOne(optional = false)
    private Department department;
    @ManyToOne(optional = false)
    private Position position;
    @ManyToOne(optional = false)
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionHistory that = (PositionHistory) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
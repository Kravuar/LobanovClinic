package net.kravuar.lobanovclinic.domain.model.history;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Table(name = "hospitalization_history")
public class HospitalizationHistory {
    @EmbeddedId
    private HumanTimestampKey key;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("humanId")
    private Human patient;

    @ManyToOne(optional = false)
    private Department department;
    @ManyToOne(optional = false)
    private Status status;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Human medic;

    @Min(value = 0, message = "Номер палаты не может быть отрицательным.")
    private int room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HospitalizationHistory that = (HospitalizationHistory) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
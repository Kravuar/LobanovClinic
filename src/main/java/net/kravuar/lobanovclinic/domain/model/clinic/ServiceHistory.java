package net.kravuar.lobanovclinic.domain.model.clinic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.util.HumanTimestampKey;
import net.kravuar.lobanovclinic.domain.model.users.Human;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "service_history")
public class ServiceHistory {
    @EmbeddedId
    private HumanTimestampKey key;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("passport")
    private Human patient;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Service service;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Human medic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceHistory that = (ServiceHistory) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
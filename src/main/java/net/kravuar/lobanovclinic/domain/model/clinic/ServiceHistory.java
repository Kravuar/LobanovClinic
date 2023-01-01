package net.kravuar.lobanovclinic.domain.model.clinic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.util.HumanTimestampKey;
import net.kravuar.lobanovclinic.domain.model.users.Human;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public ServiceHistory(Human patient, Service service, Human medic) {
        this(patient, service, medic, LocalDateTime.now());
    }
    public ServiceHistory(Human patient, Service service, Human medic, LocalDateTime timestamp) {
        this.key = new HumanTimestampKey(patient.getPassport(), timestamp);
        this.patient = patient;
        this.service = service;
        this.medic = medic;
    }

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
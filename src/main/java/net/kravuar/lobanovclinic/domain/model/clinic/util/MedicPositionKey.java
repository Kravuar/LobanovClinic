package net.kravuar.lobanovclinic.domain.model.clinic.util;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MedicPositionKey implements Serializable {
    @Column(nullable = false)
    Long medic_passport;

    @Column(nullable = false)
    Long position;

    @Column(nullable = false)
    Long department;
}

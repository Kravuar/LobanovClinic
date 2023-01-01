package net.kravuar.lobanovclinic.domain.model.clinic.util;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class HumanTimestampKey implements Serializable {
    @Column(nullable = false)
    Long passport;

    @Column(nullable = false)
    LocalDateTime timestamp;
}
package net.kravuar.lobanovclinic.domain.model.history.util;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
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
    Long humanId;

    @Column(nullable = false)
    LocalDateTime timestamp;
}

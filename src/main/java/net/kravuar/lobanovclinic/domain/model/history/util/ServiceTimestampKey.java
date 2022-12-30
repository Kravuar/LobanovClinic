package net.kravuar.lobanovclinic.domain.model.history.util;

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
public class ServiceTimestampKey implements Serializable {
    @Column(nullable = false)
    Long serviceId;

    @Column(nullable = false)
    LocalDateTime timestamp;
}

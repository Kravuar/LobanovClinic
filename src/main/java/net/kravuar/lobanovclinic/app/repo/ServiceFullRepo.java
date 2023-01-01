package net.kravuar.lobanovclinic.app.repo;

import net.kravuar.lobanovclinic.domain.model.clinic.ServiceFull;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceFullRepo extends ReadOnlyRepository<ServiceFull, Long> {
}
package net.kravuar.lobanovclinic.app.repo;

import net.kravuar.lobanovclinic.domain.model.clinic.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceHistoryRepo extends JpaRepository<ServiceHistory, Long> {
}
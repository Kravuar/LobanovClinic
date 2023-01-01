package net.kravuar.lobanovclinic.app.repo;

import net.kravuar.lobanovclinic.domain.model.clinic.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository<Service, Long> {
}
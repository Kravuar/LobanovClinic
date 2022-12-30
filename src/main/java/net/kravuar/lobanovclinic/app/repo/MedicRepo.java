package net.kravuar.lobanovclinic.app.repo;

import net.kravuar.lobanovclinic.domain.model.users.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicRepo extends JpaRepository<Medic, Long> {
    Optional<Medic> findByUsername(String username);
    Optional<Medic> findByHumanId(Long id);
    boolean existsByUsername(String username);
}

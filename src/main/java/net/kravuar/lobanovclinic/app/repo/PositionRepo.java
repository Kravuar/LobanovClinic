package net.kravuar.lobanovclinic.app.repo;

import net.kravuar.lobanovclinic.domain.model.clinic.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepo extends JpaRepository<Position, Long> {
    Optional<Position> findByName(String name);
}

package net.kravuar.lobanovclinic.app.repo;

import net.kravuar.lobanovclinic.domain.model.clinic.MedicPosition;
import net.kravuar.lobanovclinic.domain.model.clinic.util.MedicPositionKey;
import net.kravuar.lobanovclinic.domain.model.users.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicPositionsRepo extends JpaRepository<MedicPosition, Long> {
    void deleteByKey(MedicPositionKey key);
}

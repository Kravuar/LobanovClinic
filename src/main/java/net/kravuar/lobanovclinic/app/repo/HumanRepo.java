package net.kravuar.lobanovclinic.app.repo;

import net.kravuar.lobanovclinic.domain.model.users.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepo extends JpaRepository<Human, Long> {
}

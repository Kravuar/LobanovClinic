package net.kravuar.lobanovclinic.app.repo;

import net.kravuar.lobanovclinic.domain.model.users.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HumanRepo extends JpaRepository<Human, Long> {
    Optional<Human> findByPassport(Long passport);
}

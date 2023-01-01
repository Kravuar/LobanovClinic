package net.kravuar.lobanovclinic.app.repo;

import net.kravuar.lobanovclinic.domain.model.users.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPassport(Long passport);
    List<Patient> findAllByDepartmentId(Long id);
}
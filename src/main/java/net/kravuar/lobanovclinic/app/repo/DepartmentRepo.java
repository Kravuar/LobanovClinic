package net.kravuar.lobanovclinic.app.repo;

import net.kravuar.lobanovclinic.domain.model.clinic.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
package net.kravuar.lobanovclinic.app.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.repo.DepartmentRepo;
import net.kravuar.lobanovclinic.domain.model.clinic.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    public Department findById(Long id) { return departmentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Отделение с номером " + id + " не найдено.")); }
}

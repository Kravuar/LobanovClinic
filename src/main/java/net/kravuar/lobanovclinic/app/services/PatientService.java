package net.kravuar.lobanovclinic.app.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.repo.PatientRepo;
import net.kravuar.lobanovclinic.domain.dto.PatientFormDTO;
import net.kravuar.lobanovclinic.domain.model.users.Patient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepo patientRepo;
    private final HumanService humanService;
    private final MedicService medicService;
    private final DepartmentService departmentService;

    public Patient findByPassport(Long passport) { return patientRepo.findByPassport(passport).orElseThrow(() -> new EntityNotFoundException("Пациент с номером паспорта " + passport + " не найдена.")); }
    public List<Patient> findByDepartment(Long departmentId) { return patientRepo.findAllByDepartmentId(departmentId); }
    public void save(PatientFormDTO patientFormDTO) {
        savePatientTo(patientFormDTO, new Patient());
    }
    public void save(Patient patient) {
        patientRepo.save(patient);
    }
    public void deleteByPassport(Long passport) {
        patientRepo.deleteById(passport);
    }
    private void savePatientTo(PatientFormDTO patientFormDTO, Patient patient) {
        patient.setHuman(humanService.findHumanByPassport(patientFormDTO.getPatientPassport()));
        patient.setDepartment(departmentService.findById(patientFormDTO.getDepartmentId()));
        patient.setWard(patientFormDTO.getWard());
        patient.setMedic(medicService.findMedicByPassport(patientFormDTO.getMedicPassport()));

        patientRepo.save(patient);
    }
}
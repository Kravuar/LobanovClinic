package net.kravuar.lobanovclinic.app.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.repo.PatientRepo;
import net.kravuar.lobanovclinic.domain.dto.PatientFormDTO;
import net.kravuar.lobanovclinic.domain.model.users.Patient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepo patientRepo;
    private final HumanService humanService;
    private final MedicService medicService;
    private final DepartmentService departmentService;

    public Patient findByPassport(Long passport) { return patientRepo.findByPassport(passport).orElseThrow(() -> new EntityNotFoundException("Пациент с номером паспорта" + passport + " не найдена.")); }
    public List<Patient> findByDepartment(Long departmentId) { return patientRepo.findAllByDepartmentId(departmentId); }
    public void save(PatientFormDTO patientFormDTO) {
        savePatientTo(patientFormDTO, new Patient());
    }
    public void update(PatientFormDTO patientFormDTO, Long patientPassport) {
        savePatientTo(patientFormDTO, findByPassport(patientPassport));
    }
    public void deleteById(Long id) {
        patientRepo.deleteById(id);
    }
    private void savePatientTo(PatientFormDTO patientFormDTO, Patient patient) {
        patient.setHuman(humanService.findHumanByPassport(patientFormDTO.getPatientPassport()));
        patient.setDepartment(departmentService.findById(patientFormDTO.getDepartmentId()));
        patient.setWard(patientFormDTO.getWard());
        patient.setMedic(medicService.findMedicByPassport(patientFormDTO.getMedicPassport()));

        patientRepo.save(patient);
    }
}

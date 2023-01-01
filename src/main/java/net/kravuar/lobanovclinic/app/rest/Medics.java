package net.kravuar.lobanovclinic.app.rest;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.services.DepartmentService;
import net.kravuar.lobanovclinic.app.services.MedicService;
import net.kravuar.lobanovclinic.app.services.PatientService;
import net.kravuar.lobanovclinic.app.services.ServiceService;
import net.kravuar.lobanovclinic.domain.dto.PatientDTO;
import net.kravuar.lobanovclinic.domain.dto.PatientFormDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/medics")
@RequiredArgsConstructor
@Validated
public class Medics {
    private final PatientService patientService;
    private final ServiceService serviceService;
    private final MedicService medicService;
    private final DepartmentService departmentService;

    @GetMapping("/ofDepartment/{departmentId}")
    public ResponseEntity<List<>>

    @PostMapping("/hospitalize")
    public void hospitalize(PatientFormDTO patientFormDTO) {
        patientService.save(patientFormDTO);
    }

    @DeleteMapping("/discharge/{patientPassport}")
    public void discharge(@Min(0) @PathVariable Long patientPassport) {
        patientService.deleteByPassport(patientPassport);
    }

    @PutMapping("/scheduleService/{patientPassport}/{medicPassport}/{serviceId}")
    public void scheduleService(@Min(0) @PathVariable Long patientPassport, @Min(0) @PathVariable Long medicPassport, @Min(0) @PathVariable Long serviceId) {
        serviceService.addToHistory(patientPassport, serviceId, medicPassport, LocalDateTime.now());
    }

    @PutMapping("/assignMedicToPatient/{patientPassport}/{medicPassport}")
    public void assignMedic(@Min(0) @PathVariable Long patientPassport, @Min(0) @PathVariable Long medicPassport) {
        var patient = patientService.findByPassport(patientPassport);
        patient.setMedic(medicService.findMedicByPassport(medicPassport));
        patientService.save(patient);
    }

    @PutMapping("/movePatientDepartmentwise/{patientPassport}/{departmentId}")
    public void moveDepartmentwise(@Min(0) @PathVariable Long patientPassport, @Min(0) @PathVariable Long departmentId) {
        var patient = patientService.findByPassport(patientPassport);
        patient.setDepartment(departmentService.findById(departmentId));
        patientService.save(patient);
    }

    @PutMapping("/movePatientWardwise/{patientPassport}/{ward}")
    public void moveWardwise(@Min(0) @PathVariable Long patientPassport, @Min(0) @PathVariable Integer ward) {
        var patient = patientService.findByPassport(patientPassport);
        patient.setWard(ward);
        patientService.save(patient);
    }
}
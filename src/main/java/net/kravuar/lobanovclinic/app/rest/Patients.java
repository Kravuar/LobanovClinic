package net.kravuar.lobanovclinic.app.rest;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.services.MedicService;
import net.kravuar.lobanovclinic.app.services.PatientService;
import net.kravuar.lobanovclinic.domain.dto.PatientDTO;
import net.kravuar.lobanovclinic.domain.dto.PatientFullDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Validated
public class Patients {
    private final MedicService medicService;
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientFullDTO>> getAll() {
        return ResponseEntity.ok(patientService
                .findAll().stream()
                .map(PatientFullDTO::new)
                .toList()
        );
    }

    @GetMapping("/patientsOfMedic/{medicPassport}")
    public ResponseEntity<List<PatientDTO>> getPatientsOf(@Min(0) @PathVariable Long medicPassport) {
        return ResponseEntity.ok(medicService
                .findMedicByPassport(medicPassport)
                .getPatients()
                .stream().map(PatientDTO::new)
                .toList()
        );
    }

    @GetMapping("/patientsInDepartment/{departmentId}")
    public ResponseEntity<List<PatientFullDTO>> getPatientsIn(@Min(0) @PathVariable Long departmentId) {
        return ResponseEntity.ok(
                patientService.findByDepartment(departmentId)
                .stream().map(PatientFullDTO::new)
                .toList()
        );
    }
}
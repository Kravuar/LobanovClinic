package net.kravuar.lobanovclinic.app.rest;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.services.HumanService;
import net.kravuar.lobanovclinic.app.services.MedicService;
import net.kravuar.lobanovclinic.app.services.PatientService;
import net.kravuar.lobanovclinic.app.services.ServiceService;
import net.kravuar.lobanovclinic.domain.dto.PatientDTO;
import net.kravuar.lobanovclinic.domain.dto.PatientFormDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Validated
public class Patients {
    private final MedicService medicService;
    private final PatientService patientService;

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
    public ResponseEntity<List<PatientDTO>> getPatientsIn(@Min(0) @PathVariable Long departmentId) {
        return ResponseEntity.ok(
                patientService.findByDepartment(departmentId)
                .stream().map(PatientDTO::new)
                .toList()
        );
    }
}
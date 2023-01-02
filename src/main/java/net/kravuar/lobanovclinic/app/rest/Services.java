package net.kravuar.lobanovclinic.app.rest;

import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.services.ServiceService;
import net.kravuar.lobanovclinic.domain.dto.ServiceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
@Validated
public class Services {
    private final ServiceService serviceService;

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getPatientsIn() {
        return ResponseEntity.ok(
                serviceService.findAllServices().stream()
                .map(ServiceDTO::new)
                .toList()
        );
    }
}
package net.kravuar.lobanovclinic.app.rest;

import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.services.PositionService;
import net.kravuar.lobanovclinic.app.services.ServiceService;
import net.kravuar.lobanovclinic.domain.dto.PositionDTO;
import net.kravuar.lobanovclinic.domain.dto.ServiceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
@Validated
public class Positions {
    private final PositionService positionService;

    @GetMapping
    public ResponseEntity<List<PositionDTO>> getPositions() {
        return ResponseEntity.ok(
                positionService.findAll().stream()
                .map(PositionDTO::new)
                .toList()
        );
    }
}
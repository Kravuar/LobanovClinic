package net.kravuar.lobanovclinic.app.rest;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.services.HumanService;
import net.kravuar.lobanovclinic.domain.dto.HumanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/humans")
@RequiredArgsConstructor
@Validated
public class Humans {
    private final HumanService humanService;

    @GetMapping
    public ResponseEntity<List<HumanDTO>> getAll() {
        return ResponseEntity.ok(humanService
                .findAll().stream()
                .map(HumanDTO::new)
                .toList()
        );
    }

    @GetMapping("{passport}")
    public ResponseEntity<HumanDTO> byPassport(@Min(0) @PathVariable Long passport) {
        return ResponseEntity.ok(new HumanDTO(humanService.findHumanByPassport(passport)));
    }
}
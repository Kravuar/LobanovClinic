package net.kravuar.lobanovclinic.app.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.repo.PositionRepo;
import net.kravuar.lobanovclinic.domain.model.clinic.Position;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepo positionRepo;

    public List<Position> findAll() { return positionRepo.findAll(); }
    public Position findById(Long id) { return positionRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Должность с номером " + id + " не найдена.")); }
}

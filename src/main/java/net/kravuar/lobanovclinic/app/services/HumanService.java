package net.kravuar.lobanovclinic.app.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.repo.HumanRepo;
import net.kravuar.lobanovclinic.domain.dto.HumanDTO;
import net.kravuar.lobanovclinic.domain.model.users.Human;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HumanService {
    private final HumanRepo humanRepo;

    public List<Human> findAll() { return humanRepo.findAll(); }

    public Human findHumanByPassport(Long passport) {
        return humanRepo.findByPassport(passport)
                .orElseThrow(() -> new EntityNotFoundException("Человек с номером паспорта " + passport + " не найден."));
    }

    private void saveHumanTo(HumanDTO humanDTO, Human human) {
        human.setFullName(humanDTO.getFullName());
        human.setDateOfBirth(humanDTO.getDateOfBirth());

        humanRepo.save(human);
    }
    public void saveHuman(HumanDTO humanDTO) {
        saveHumanTo(humanDTO, new Human());
    }
    public void deleteHumanByPassport(Long passport) {
        humanRepo.deleteById(passport);
    }
}
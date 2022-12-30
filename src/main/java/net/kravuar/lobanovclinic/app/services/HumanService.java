package net.kravuar.lobanovclinic.app.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.repo.HumanRepo;
import net.kravuar.lobanovclinic.app.repo.MedicRepo;
import net.kravuar.lobanovclinic.app.repo.PositionRepo;
import net.kravuar.lobanovclinic.domain.dto.HumanDTO;
import net.kravuar.lobanovclinic.domain.dto.MedicFormDTO;
import net.kravuar.lobanovclinic.domain.model.users.Human;
import net.kravuar.lobanovclinic.domain.model.users.Medic;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class HumanService implements UserDetailsService {
    private final PositionRepo positionRepo;
    private final PasswordEncoder encoder;
    private final HumanRepo humanRepo;
    private final MedicRepo medicRepo;

    public Medic findMedicByUsername(String username) {
        return medicRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Врач с именем пользователя " + username + " не найден."));
    }
    public Medic findMedicByHumanId(Long id) {
        return medicRepo.findByHumanId(id)
                .orElseThrow(() -> new EntityNotFoundException("Врач с номером человека " + id + " не найден."));
    }

    private void saveMedicTo(MedicFormDTO medicFormDTO, Medic medic) {
        medic.setUsername(medicFormDTO.getUsername());
        medic.setPassword(encoder.encode(medicFormDTO.getPassword()));
        medic.setHuman(humanRepo.findById(medicFormDTO.getHumanId()).orElseThrow(() -> new EntityNotFoundException("Человек с номером " + medicFormDTO.getHumanId() + " не найден.")));
//        medicPositions.setPosition(positionRepo.findByName(medicFormDTO.getPosition()).orElseThrow(EntityNotFoundException::new));

        medicRepo.save(medic);
    }
    private void saveHumanTo(HumanDTO humanDTO, Human human) {
        human.setFullName(humanDTO.getFullName());
        human.setDateOfBirth(humanDTO.getDateOfBirth());

        humanRepo.save(human);
    }
    public void saveHuman(HumanDTO humanDTO) {
        saveHumanTo(humanDTO, new Human());
    }
    public void signup(MedicFormDTO medicFormDTO) {
        if(medicRepo.existsByUsername(medicFormDTO.getUsername()))
            throw new EntityExistsException("Врач с именем пользователя " + medicFormDTO.getUsername() + " уже существует.");

        saveMedicTo(medicFormDTO, new Medic());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var medic = findMedicByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                medic.getUsername(),
                medic.getPassword(),
                Set.of()); // medicPositions.map to Pos:rank
    }
}
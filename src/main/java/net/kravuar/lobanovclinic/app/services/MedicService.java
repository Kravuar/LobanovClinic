package net.kravuar.lobanovclinic.app.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.repo.MedicPositionsRepo;
import net.kravuar.lobanovclinic.app.repo.MedicRepo;
import net.kravuar.lobanovclinic.domain.dto.MedicFormDTO;
import net.kravuar.lobanovclinic.domain.dto.MedicPositionFormDTO;
import net.kravuar.lobanovclinic.domain.model.clinic.MedicPosition;
import net.kravuar.lobanovclinic.domain.model.clinic.util.MedicPositionKey;
import net.kravuar.lobanovclinic.domain.model.users.Medic;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicService implements UserDetailsService {
    private final HumanService humanService;
    private final MedicRepo medicRepo;
    private final MedicPositionsRepo medicPositionsRepo;
    private final PasswordEncoder encoder;
    private final PositionService positionService;
    private final DepartmentService departmentService;

    public List<Medic> findAll() { return medicRepo.findAll(); }
    public List<Medic> findByDepartment(Long departmentId) { return departmentService
            .findById(departmentId)
            .getPositions().stream()
            .map(MedicPosition::getMedic)
            .toList();
    }
    public Medic findMedicByUsername(String username) {
        return medicRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Врач с именем пользователя " + username + " не найден."));
    }
    public Medic findMedicByPassport(Long passport) {
        return medicRepo.findByPassport(passport)
                .orElseThrow(() -> new EntityNotFoundException("Врач с номером паспорта " + passport + " не найден."));
    }

    private void saveMedicTo(MedicFormDTO medicFormDTO, Medic medic) {
        medic.setUsername(medicFormDTO.getUsername());
        medic.setPassword(encoder.encode(medicFormDTO.getPassword()));
        medic.setHuman(humanService.findHumanByPassport(medicFormDTO.getPassport()));
        medic.getPositions()
                .addAll(medicFormDTO
                        .getPositions().stream()
                        .map(position ->
                                new MedicPosition(
                                        findMedicByPassport(medicFormDTO.getPassport()),
                                        departmentService.findById(position.getDepartmentId()),
                                        positionService.findById(position.getPositionId())
                                )
                        ).toList()
                );
        medicRepo.save(medic);
    }
    public void signup(MedicFormDTO medicFormDTO) {
        if(medicRepo.existsByUsername(medicFormDTO.getUsername()))
            throw new EntityExistsException("Врач с именем пользователя " + medicFormDTO.getUsername() + " уже существует.");

        saveMedicTo(medicFormDTO, new Medic());
    }
    public void update(MedicFormDTO medicFormDTO, Medic medic) {
        saveMedicTo(medicFormDTO, medic);
    }
    public void deleteMedicByPassport(Long passport) {
        medicRepo.deleteById(passport);
    }
    public void addPosition(Long passport, MedicPositionFormDTO positionFormDTO) {
        medicPositionsRepo.save(new MedicPosition(
                    findMedicByPassport(passport),
                    departmentService.findById(positionFormDTO.getDepartmentId()),
                    positionService.findById(positionFormDTO.getPositionId())
                )
        );
    }
    public void removePosition(Long passport, MedicPositionFormDTO positionFormDTO) {
        medicPositionsRepo.deleteByKey(new MedicPositionKey(
                    passport,
                    positionFormDTO.getPositionId(),
                    positionFormDTO.getDepartmentId()
                )
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var medic = findMedicByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                medic.getUsername(),
                medic.getPassword(),
                medic.getPositions().stream()
                        .map(MedicPosition::getPosition)
                        .collect(Collectors.toSet()));
    }
}
package net.kravuar.lobanovclinic.app.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.repo.PriceHistoryRepo;
import net.kravuar.lobanovclinic.app.repo.ServiceFullRepo;
import net.kravuar.lobanovclinic.app.repo.ServiceHistoryRepo;
import net.kravuar.lobanovclinic.app.repo.ServiceRepo;
import net.kravuar.lobanovclinic.domain.dto.ServiceDTO;
import net.kravuar.lobanovclinic.domain.model.clinic.PriceHistory;
import net.kravuar.lobanovclinic.domain.model.clinic.Service;
import net.kravuar.lobanovclinic.domain.model.clinic.ServiceFull;
import net.kravuar.lobanovclinic.domain.model.clinic.ServiceHistory;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceHistoryRepo serviceHistoryRepo;
    private final PriceHistoryRepo priceHistoryRepo;
    private final ServiceFullRepo serviceFullRepo;
    private final PatientService patientService;
    private final MedicService medicService;
    private final ServiceRepo serviceRepo;

    public Service findById(Long id) {
        return serviceRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Услуга с номером " + id + " не найдена."));
    }
    public ServiceFull findFullById(Long id) {
        return serviceFullRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Услуга с номером " + id + " не найдена."));
    }
    public List<ServiceFull> findAllServices() {
        return serviceFullRepo.findAll();
    }
    public void changePrice(Long serviceId, float newPrice) {
        priceHistoryRepo.save(new PriceHistory(LocalDateTime.now(), findById(serviceId), newPrice));
    }
    public void addService(ServiceDTO serviceDTO) {
        var service = new Service();
        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());

        var saved = serviceRepo.save(service);
        serviceRepo.flush();

        changePrice(saved.getId(), serviceDTO.getPrice());
    }
    public void addToHistory(Long patientPassport, Long serviceId, Long medicPassport, LocalDateTime timestamp) {
        serviceHistoryRepo.save(
                new ServiceHistory(
                        patientService.findByPassport(patientPassport).getHuman(),
                        findById(serviceId),
                        medicService.findMedicByPassport(medicPassport).getHuman(),
                        timestamp
                )
        );
    }
}
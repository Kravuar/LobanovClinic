package net.kravuar.lobanovclinic.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.clinic.ServiceFull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceDTO {
    @Size(min = 5, max = 30, message = "Название услуги должно быть от 5 до 30 символов.")
    private String name;

    @Size(max = 512, message = "Описание услуги должно быть не более 512 символов.")
    private String description;

    @Min(value = 0, message = "Цена не может быть отрицательной.")
    private float price;

    public ServiceDTO(ServiceFull serviceFull) {
        this.name = serviceFull.getName();
        this.description = serviceFull.getDescription();
        this.price = serviceFull.getPrice();
    }
}
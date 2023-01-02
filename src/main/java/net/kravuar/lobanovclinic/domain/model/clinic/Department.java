package net.kravuar.lobanovclinic.domain.model.clinic;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import net.kravuar.lobanovclinic.domain.model.users.Patient;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "departaments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 5, max = 30, message = "Название отделения должно быть от 5 до 30 символов.")
    private String name;

    @OneToMany(mappedBy = "department", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Patient> patients = new HashSet<>();

    @OneToMany(mappedBy = "department", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MedicPosition> positions = new HashSet<>();
}
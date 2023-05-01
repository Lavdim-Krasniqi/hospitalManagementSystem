package ikubinfo.hospital.management.system.patient.entity;

import ikubinfo.hospital.management.system.diagnose.entity.Diagnose;
import ikubinfo.hospital.management.system.medical_recipe.entity.MedicalRecipe;
import ikubinfo.hospital.management.system.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Indexed;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "patient",
    indexes = @Index(name = "patient_personalNumber", unique = true, columnList = "personalNumber"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String surname;
  private Long personalNumber;
  private Date birthDate;
  @CreatedDate private Instant createdDate;
  @LastModifiedDate private Instant lastModifiedDate;

  @OneToMany(mappedBy = "patient")
  private Set<Schedule> schedules = new HashSet<>();

  @OneToMany(mappedBy = "patient")
  private Set<Diagnose> diagnoses = new HashSet<>();

  @OneToMany(mappedBy = "patient")
  private Set<MedicalRecipe> medicalRecipes = new HashSet<>();

  public PatientDto getPatientDto() {
    return PatientDto.builder()
        .id(this.id)
        .name(this.name)
        .birthDate(this.birthDate)
        .personalNumber(this.personalNumber)
        .surname(this.surname)
        .createdDate(this.createdDate)
        .lastModifiedDate(this.lastModifiedDate)
        .build();
  }

}

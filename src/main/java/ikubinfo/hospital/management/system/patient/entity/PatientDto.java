package ikubinfo.hospital.management.system.patient.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@Data
@Builder
public class PatientDto {

  private Long id;

  private String name;
  private String surname;
  private Long personalNumber;
  private Date birthDate;
  private Instant createdDate;
  private Instant lastModifiedDate;

  public Patient getPatientFromDto() {
    return new Patient(
            this.getId(),
            this.getName(),
            this.getSurname(),
            this.getPersonalNumber(),
            this.getBirthDate(),
            this.getCreatedDate(),
            this.getLastModifiedDate(),
            null,
            null,
            null);
  }
}

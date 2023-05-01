package ikubinfo.hospital.management.system.diagnose.entity;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.patient.entity.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Table(name = "diagnose")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Diagnose {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String diagnose;
  @CreatedDate private Instant createdDate;
  @LastModifiedDate private Instant lastModifiedDate;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;
}

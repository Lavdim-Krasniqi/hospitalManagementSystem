package ikubinfo.hospital.management.system.department.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ikubinfo.hospital.management.system.diagnose.entity.Diagnose;
import ikubinfo.hospital.management.system.doctor.entity.Doctor;
import ikubinfo.hospital.management.system.hospital.entity.Hospital;
import ikubinfo.hospital.management.system.schedule.entity.Schedule;
import ikubinfo.hospital.management.system.work_schedule.entity.WorkSchedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "department",
    indexes = {@Index(name = "name_hospital", columnList = "name, hospital_id", unique = true)})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @CreatedDate private Instant createdDate;
  @LastModifiedDate private Instant lastModifiedDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hospital_id")
  @JsonIgnore
  Hospital hospital1;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinTable(
      name = "doctor_department",
      joinColumns = @JoinColumn(name = "department_fk"),
      inverseJoinColumns = @JoinColumn(name = "doctor_fk"))
  private Set<Doctor> doctors = new HashSet<>();

  @OneToMany(mappedBy = "department")
  private Set<WorkSchedule> workSchedules = new HashSet<>();

  @OneToMany(mappedBy = "department")
  private Set<Schedule> schedules = new HashSet<>();

  @OneToMany(mappedBy = "department")
  private Set<Diagnose> diagnoses = new HashSet<>();
}

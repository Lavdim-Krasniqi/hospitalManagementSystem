package ikubinfo.hospital.management.system.work_schedule.entity;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.doctor.entity.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Table(
    name = "work_schedule",
    indexes = {
      @Index(name = "workSchedule", unique = true, columnList = "timeFrom, timeTo, doctor_id")
    })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkSchedule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate private Instant createdDate;
  @LastModifiedDate private Instant lastModifiedDate;

  private Instant timeFrom;
  private Instant timeTo;

  @JoinColumn(name = "doctor_id")
  private Long doctorId;

  @JoinColumn(name = "department_id")
  private Long departmentId;

  @ManyToOne
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  public WorkScheduleDto getWorkScheduleDto() {
    return WorkScheduleDto.builder()
        .id(this.getId())
        .createdDate(this.getCreatedDate())
        .lastModifiedDate(this.getLastModifiedDate())
        .timeFrom(this.getTimeFrom())
        .timeTo(this.getTimeTo())
        .doctorId(this.doctorId)
        .departmentId(this.getDepartmentId())
        .build();
  }
}

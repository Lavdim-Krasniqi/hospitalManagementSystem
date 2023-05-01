package ikubinfo.hospital.management.system.doctor.entity;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.medical_recipe.entity.MedicalRecipe;
import ikubinfo.hospital.management.system.schedule.entity.Schedule;
import ikubinfo.hospital.management.system.work_schedule.entity.WorkSchedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.awt.print.Book;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "doctor",
    indexes = {@Index(name = "personalNumber", columnList = "personalNumber", unique = true)})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Doctor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Long personalNumber;
  private String specialization;
  @CreatedDate private Instant createdDate;
  @LastModifiedDate private Instant lastModifiedDate;

  @ManyToMany(
      mappedBy = "doctors",
      cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
  Set<Department> departments = new HashSet<>();

  @OneToMany(mappedBy = "doctor")
  Set<WorkSchedule> workSchedule = new HashSet<>();

  @OneToMany(mappedBy = "doctor")
  private Set<Schedule> schedules = new HashSet<>();

  @OneToMany(mappedBy = "doctor")
  private Set<MedicalRecipe> medicalRecipes = new HashSet<>();

  public void removeDepartment(Department department) {
    this.departments.remove(department);
    department.getDoctors().remove(this);
  }
}

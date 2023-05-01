package ikubinfo.hospital.management.system.hospital.entity;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.medical_recipe.entity.MedicalRecipe;
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
    name = "hospital",
    indexes = {@Index(name = "name_location", columnList = "name, location", unique = true)})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Hospital {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String location;
  private String state;
  private String city;

  @CreatedDate private Instant createdDate;
  @LastModifiedDate private Instant lastModifiedDate;

  @OneToMany(mappedBy = "hospital1", fetch = FetchType.LAZY)
  Set<Department> departments = new HashSet<>();

  @OneToMany(mappedBy = "hospital")
  Set<MedicalRecipe> medicalRecipes = new HashSet<>();
}

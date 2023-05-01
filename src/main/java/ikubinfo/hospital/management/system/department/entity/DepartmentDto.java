package ikubinfo.hospital.management.system.department.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class DepartmentDto {

  public Long id;
  public String name;
  public Long hospitalId;
  public Instant createdDate;
  public Instant lastModifiedDate;
}

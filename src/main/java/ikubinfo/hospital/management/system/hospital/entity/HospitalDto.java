package ikubinfo.hospital.management.system.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HospitalDto {

  private Long Id;
  private String name;
  private String location;
  private String state;
  private String city;
}

package ikubinfo.hospital.management.system.hospital.controller;

import ikubinfo.hospital.management.system.hospital.entity.Hospital;
import ikubinfo.hospital.management.system.hospital.entity.HospitalDto;
import ikubinfo.hospital.management.system.hospital.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {

  private final HospitalService service;

  @PostMapping("/addHospital")
  public HospitalDto addHospital(@RequestBody HospitalDto hospital) {
    return service.addHospital(hospital);
  }

  @GetMapping("/getHospitalById/{id}")
  public HospitalDto findHospitalById(@PathVariable Long id) {
    return service.findHospitalById(id);
  }

  @DeleteMapping("/deleteHospitalById/{id}")
  public Integer deleteById(@PathVariable Long id) {
    return service.deleteById(id);
  }
}

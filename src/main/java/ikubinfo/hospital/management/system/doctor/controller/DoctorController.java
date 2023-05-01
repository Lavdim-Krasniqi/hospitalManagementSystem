package ikubinfo.hospital.management.system.doctor.controller;

import ikubinfo.hospital.management.system.doctor.entity.Doctor;
import ikubinfo.hospital.management.system.doctor.entity.DoctorDto;
import ikubinfo.hospital.management.system.doctor.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/doctor")
public class DoctorController {

  private final DoctorService service;

  @PostMapping("/addDoctor")
  public DoctorDto addDoctor(@RequestBody DoctorDto doctor) {
    return service.addDoctor(doctor);
  }

  @PostMapping("/registerDoctorDepartment/{doctorId}/{departmentId}")
  public void addDoctorDepartment(@PathVariable Long doctorId, @PathVariable Long departmentId) {
    service.addDoctorDepartment(doctorId, departmentId);
  }

  @GetMapping("/getById/{id}")
  public DoctorDto getDoctorById(@PathVariable Long id) {
    return service.getDoctorById(id);
  }

  @DeleteMapping("/deleteById/{id}")
  public Integer deleteById(@PathVariable Long id) {
    return service.deleteById(id);
  }

  @DeleteMapping("/deleteDepartment/{doctorId}/{departmentId}")
  public void deleteDepartmentOfDoctor(
      @PathVariable Long doctorId, @PathVariable Long departmentId) {
    service.deleteDepartmentOfDoctor(doctorId, departmentId);
  }

  @DeleteMapping("/deleteAllDepartmentsOfDoctor/{doctorId}")
  public void deleteAllDepartmentsOfDoctor(@PathVariable Long doctorId) {
    service.deleteAllDepartmentsOfDoctor(doctorId);
  }
}

package ikubinfo.hospital.management.system.work_schedule.controller;

import ikubinfo.hospital.management.system.work_schedule.entity.WorkScheduleDto;
import ikubinfo.hospital.management.system.work_schedule.service.WorkScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workSchedule")
@AllArgsConstructor
public class WorkScheduleController {

  private final WorkScheduleService service;

  @PostMapping("/addWorkTime")
  public WorkScheduleDto addWorkSchedule(@RequestBody WorkScheduleDto workSchedule) {
    return service.addWorkSchedule(workSchedule);
  }

  @GetMapping("getWorkTimeById/{id}")
  public WorkScheduleDto getWorkScheduleDto(@PathVariable Long id) {
    return service.getWorkScheduleDto(id);
  }

  @DeleteMapping("/deleteByDoctorId/{doctorId}")
  public Integer deleteWorkSchedulesByDoctorId(@PathVariable Long doctorId) {
    return service.deleteWorkSchedulesByDoctorId(doctorId);
  }

  @DeleteMapping("/deleteByDepartmentId/{departmentId}")
  public Integer deleteAllWorkSchedulesOfDepartment(@PathVariable Long departmentId) {
    return service.deleteAllWorkSchedulesOfDepartment(departmentId);
  }

  @DeleteMapping("/deleteById/{id}")
  public void deleteWorkScheduleById(@PathVariable Long id) {
    service.deleteWorkScheduleById(id);
  }
}

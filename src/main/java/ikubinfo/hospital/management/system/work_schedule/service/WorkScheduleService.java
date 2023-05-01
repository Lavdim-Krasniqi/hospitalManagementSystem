package ikubinfo.hospital.management.system.work_schedule.service;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.department.service.DepartmentService;
import ikubinfo.hospital.management.system.doctor.entity.Doctor;
import ikubinfo.hospital.management.system.doctor.service.DoctorService;
import ikubinfo.hospital.management.system.exception.NotFoundException;
import ikubinfo.hospital.management.system.work_schedule.entity.WorkSchedule;
import ikubinfo.hospital.management.system.work_schedule.entity.WorkScheduleDto;
import ikubinfo.hospital.management.system.work_schedule.repository.WorkScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class WorkScheduleService {

  private final WorkScheduleRepository repo;
  private final DoctorService doctorService;

  @Transactional
  public WorkScheduleDto addWorkSchedule(WorkScheduleDto workSchedule) {
    Doctor doctor = doctorService.findById(workSchedule.getDoctorId());
    Set<Department> departments = doctor.getDepartments();
    if (doctor != null) {
      Optional<Department> department1 =
          departments.stream()
              .filter(department -> department.getId().equals(workSchedule.getDepartmentId()))
              .findFirst();
      if (department1.isPresent()) {
        WorkSchedule workSchedule1 =
            repo.addWorkSchedule(
                new WorkSchedule(
                    null,
                    null,
                    null,
                    workSchedule.getTimeFrom(),
                    workSchedule.getTimeTo(),
                    workSchedule.getDoctorId(),
                    workSchedule.getDepartmentId(),
                    doctor,
                    department1.get()));
        return workSchedule1.getWorkScheduleDto();
      } else {
        throw new NotFoundException("Doctor does not work on given department");
      }
    }
    throw new NotFoundException("Doctor does not exist");
  }

  public WorkSchedule getWorkSchedule(Long id) {
    return repo.getWorkSchedule(id);
  }

  public WorkScheduleDto getWorkScheduleDto(Long id) {
    WorkSchedule workSchedule = repo.getWorkSchedule(id);
    return workSchedule.getWorkScheduleDto();
  }

  @Transactional
  public Integer deleteWorkSchedulesByDoctorId(Long doctorId) {
    return repo.deleteWorkSchedulesByDoctorId(doctorId);
  }

  @Transactional
  public Integer deleteAllWorkSchedulesOfDepartment(Long departmentId) {
    return repo.deleteAllWorkSchedulesOfDepartment(departmentId);
  }

  @Transactional
  public void deleteWorkScheduleById(Long id) {
    repo.deleteWorkScheduleById(id);
  }
}

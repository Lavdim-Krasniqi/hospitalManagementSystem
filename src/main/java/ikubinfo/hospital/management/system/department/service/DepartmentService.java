package ikubinfo.hospital.management.system.department.service;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.department.entity.DepartmentDto;
import ikubinfo.hospital.management.system.department.repository.DepartmentRepository;
import ikubinfo.hospital.management.system.doctor.service.DoctorService;
import ikubinfo.hospital.management.system.exception.NotFoundException;
import ikubinfo.hospital.management.system.hospital.entity.Hospital;
import ikubinfo.hospital.management.system.hospital.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DepartmentService {

  private final DepartmentRepository repo;
  private final HospitalService hospitalService;
  private final DoctorService doctorService;

  public DepartmentDto addDepartment(DepartmentDto department) {
    Hospital hospital = hospitalService.findById(department.hospitalId);
    if (hospital != null) {
      Department department1 =
          repo.addDepartment(
              new Department(
                  null, department.getName(), null, null, hospital, null, null, null, null));

      return new DepartmentDto(
          department1.getId(),
          department1.getName(),
          department.getHospitalId(),
          department1.getCreatedDate(),
          department1.getLastModifiedDate());
    }
    throw new NotFoundException("Hospital does not exist");
  }

  public Department findDepartmentById(Long id) {
    return repo.findById(id);
  }

  public DepartmentDto findById(Long id) {
    Department department = repo.findById(id);
    if (department == null) throw new RuntimeException("Department does not exist");
    return new DepartmentDto(
        department.getId(),
        department.getName(),
        department.getHospital1().getId(),
        department.getCreatedDate(),
        department.getLastModifiedDate());
  }

  @Transactional
  public Integer deleteById(Long id) {
    // in query I need to specify to delete relations with doctor from join table
    repo.deleteAllDoctorsOfDepartment(id);
    return repo.deleteById(id);
  }

  @Transactional
  public Integer deleteByHospitalId(Long id) {
    // delete doctors based on departmentsIds
    repo.removeAllDoctorsOfHospital(id);
    return repo.deleteByHospitalId(id);
  }

  public Department getDepartment(Long id) {
    return repo.getDepartment(id);
  }
}

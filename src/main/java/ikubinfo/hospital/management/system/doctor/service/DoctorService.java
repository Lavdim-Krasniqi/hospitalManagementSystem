package ikubinfo.hospital.management.system.doctor.service;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.department.service.DepartmentService;
import ikubinfo.hospital.management.system.doctor.entity.Doctor;
import ikubinfo.hospital.management.system.doctor.entity.DoctorDto;
import ikubinfo.hospital.management.system.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

  @Autowired private DoctorRepository repo;
  @Autowired @Lazy private DepartmentService departmentService;

  @Transactional
  public DoctorDto addDoctor(DoctorDto doctor) {
    Doctor doctor1 =
        repo.addDoctor(
            new Doctor(
                null,
                doctor.getName(),
                doctor.getPersonalNumber(),
                doctor.getSpecialization(),
                null,
                null,
                null,
                null,
                null,
                null));
    return new DoctorDto(
        doctor1.getId(),
        doctor1.getName(),
        doctor1.getPersonalNumber(),
        doctor1.getSpecialization(),
        doctor1.getCreatedDate(),
        doctor1.getLastModifiedDate());
  }

  @Transactional
  public void addDoctorDepartment(Long doctorId, Long departmentId) {
    // per mos me u ekzekutu 1 additional query duhet qe edhe
    // getDepartment metoda me u ekzekutu ne transaksion
    Department department = departmentService.getDepartment(departmentId);
    Doctor doctor = repo.getDoctor(doctorId).get(0);
     //here U must handle if doctorExist if departmentExist...
    doctor.getDepartments().add(department);
    department.getDoctors().add(doctor);
    repo.registerDepartment(doctor);
  }

  public Doctor findById(Long id) {
    List<Doctor> doctors = repo.getDoctor(id);
    if (doctors.isEmpty()) return null;
    else return doctors.get(0);
  }

  @Transactional
  public void deleteDepartmentOfDoctor(Long doctorId, Long departmentId) {
    repo.deleteDepartmentOfDoctor(doctorId, departmentId);
  }

  @Transactional
  public Integer deleteById(Long id) {
    deleteAllDepartmentsOfDoctor(id);
    return repo.deleteById(id);
  }

  public DoctorDto getDoctorById(Long id) {
    Doctor doctor = repo.findById(id);
    return new DoctorDto(
        doctor.getId(),
        doctor.getName(),
        doctor.getPersonalNumber(),
        doctor.getSpecialization(),
        doctor.getCreatedDate(),
        doctor.getLastModifiedDate());
  }

  //  @Transactional
  //  public Integer deleteByDepartmentId(Long id) {
  //    return repo.deleteByDepartmentId(id);
  //  }

  @Transactional
  public void deleteDepartmentsOfDoctors(Long doctorId, List<Long> departmentsIds) {
    repo.deleteDepartmentsOfDoctors(doctorId, departmentsIds);
  }

  @Transactional
  public void deleteAllDepartmentsOfDoctor(Long doctorId) {
    repo.deleteAllDepartmentsOfDoctor(doctorId);
  }
}

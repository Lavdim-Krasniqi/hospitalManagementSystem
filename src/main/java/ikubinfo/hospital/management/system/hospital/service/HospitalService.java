package ikubinfo.hospital.management.system.hospital.service;

import ikubinfo.hospital.management.system.department.service.DepartmentService;
import ikubinfo.hospital.management.system.hospital.entity.Hospital;
import ikubinfo.hospital.management.system.hospital.entity.HospitalDto;
import ikubinfo.hospital.management.system.hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HospitalService {

  @Autowired private HospitalRepository repo;
  @Autowired @Lazy private DepartmentService departmentService;

  public HospitalDto addHospital(HospitalDto hospital) {
    Hospital hospital1 =
        new Hospital(
            null,
            hospital.getName(),
            hospital.getLocation(),
            hospital.getState(),
            hospital.getCity(),
            null,
            null,
            null,
            null);
    Hospital hospital2 = repo.addHospital(hospital1);
    return new HospitalDto(
        hospital2.getId(),
        hospital2.getName(),
        hospital2.getLocation(),
        hospital2.getState(),
        hospital2.getCity());
  }

  public HospitalDto findHospitalById(Long id) {
    Hospital hospital = repo.findHospitalById(id);
    return new HospitalDto(
        hospital.getId(),
        hospital.getName(),
        hospital.getLocation(),
        hospital.getState(),
        hospital.getCity());
  }
  @Transactional
  public Integer deleteById(Long id) {
    departmentService.deleteByHospitalId(id);
    return repo.deleteById(id);
  }

  public Hospital findById(Long id) {
    return repo.findHospitalById(id);
  }
}

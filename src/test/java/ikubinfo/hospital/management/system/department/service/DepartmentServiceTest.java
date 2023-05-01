package ikubinfo.hospital.management.system.department.service;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.department.entity.DepartmentDto;
import ikubinfo.hospital.management.system.department.repository.DepartmentRepository;
import ikubinfo.hospital.management.system.hospital.entity.Hospital;
import ikubinfo.hospital.management.system.hospital.service.HospitalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

  @Mock private DepartmentRepository repo;
  @Mock private HospitalService hospitalService;
  @InjectMocks private DepartmentService service;

  @Test
  void addDepartment() {
    Department department =
        new Department(1L, "kirurgjia", null, null, null, null, null, null, null);
    DepartmentDto insertedRecord = new DepartmentDto(1L, "kirurgjia", 1L, null, null);
    Mockito.when(hospitalService.findById(Mockito.any())).thenReturn(new Hospital());
    Mockito.when(repo.addDepartment(Mockito.any())).thenReturn(department);
    DepartmentDto departmentDto = service.addDepartment(insertedRecord);
    Mockito.verify(hospitalService).findById(1L);
    Mockito.verify(repo).addDepartment(Mockito.any());

    assertEquals(departmentDto, insertedRecord);
  }

  @Test
  void addDepartmentWhenHospitalDoesNotExist() {
    DepartmentDto insertedRecord = new DepartmentDto(1L, "kirurgjia", 1L, null, null);
    Mockito.when(hospitalService.findById(Mockito.any())).thenReturn(null);
    assertThrows(
        RuntimeException.class,
        () -> {
          service.addDepartment(insertedRecord);
        },
        "Hospital does not exist");
    Mockito.verify(hospitalService).findById(1L);
  }

  @Test
  void findDepartmentById() {
    Department department = new Department();
    Mockito.when(repo.findById(Mockito.any())).thenReturn(department);
    Department departmentById = service.findDepartmentById(1L);
    Mockito.verify(repo).findById(Mockito.any());
    assertEquals(department, departmentById);
  }

  @Test
  void findById() {}

  @Test
  void deleteById() {}

  @Test
  void deleteByHospitalId() {}

  @Test
  void getDepartment() {}
}

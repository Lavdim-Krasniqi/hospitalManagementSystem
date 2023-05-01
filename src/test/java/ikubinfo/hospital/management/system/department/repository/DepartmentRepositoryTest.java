package ikubinfo.hospital.management.system.department.repository;

import ikubinfo.hospital.management.system.department.entity.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(DepartmentRepository.class)
class DepartmentRepositoryTest {

  @Autowired
  private DepartmentRepository repo;

  @Test
  void addDepartment() {
    Department department = new Department(null, "data1", null, null, null, null, null, null, null);

    Department department1 = repo.addDepartment(department);
    assertNotNull(department1);
    Department department2 = repo.findById(department1.getId());
    System.out.println(department2.getId()+ " " + department2.getCreatedDate());
    assertNotNull(department2);
  }

  @Test
  void findById() {}
}

package ikubinfo.hospital.management.system.department.repository;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.doctor.entity.Doctor;
import ikubinfo.hospital.management.system.hospital.entity.Hospital;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Delete;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class DepartmentRepository {

  private final EntityManager entityManager;

  @Transactional
  public Department addDepartment(Department department) {
    return entityManager.merge(department);
  }

  public Department findById(Long id) {
    return entityManager.find(Department.class, id);
  }

  @Transactional
  public Integer deleteById(Long id) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaDelete<Department> criteriaDelete = cb.createCriteriaDelete(Department.class);
    Root<Department> root = criteriaDelete.from(Department.class);
    criteriaDelete.where(cb.equal(root.get("id"), id));
    return entityManager.createQuery(criteriaDelete).executeUpdate();
  }

  public Integer deleteByHospitalId(Long id) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaDelete<Department> criteriaDelete = cb.createCriteriaDelete(Department.class);
    Root<Department> root = criteriaDelete.from(Department.class);
    //    root.fetch("hospital1", JoinType.LEFT);
    criteriaDelete.where(cb.equal(root.get("hospital1").get("id"), id));

    return entityManager.createQuery(criteriaDelete).executeUpdate();
  }

  public Department getDepartment(Long id) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Department> query = cb.createQuery(Department.class);
    Root<Department> root = query.from(Department.class);
    root.fetch("doctors", JoinType.LEFT);
    query.where(cb.equal(root.get("id"), id));

    return entityManager.createQuery(query).getSingleResult();
  }

  public void removeAllDoctorsOfHospital(Long hospitalId) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Department> query = cb.createQuery(Department.class);
    Root<Department> root = query.from(Department.class);
    root.fetch("doctors", JoinType.LEFT);

    query.where(cb.equal(root.get("hospital1").get("id"), hospitalId));

    List<Department> departments = entityManager.createQuery(query).getResultList();

    for (Department department : departments) {
      Set<Doctor> doctors = department.getDoctors();
      for (Doctor doctor : doctors) {
        doctor.getDepartments().remove(department);
        department.getDoctors().remove(doctor);
      }
      entityManager.persist(department);
    }
  }

  public void deleteAllDoctorsOfDepartment(Long departmentId) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Department> query = cb.createQuery(Department.class);
    Root<Department> root = query.from(Department.class);
    root.fetch("doctors", JoinType.LEFT);
    query.where(cb.equal(root.get("id"), departmentId));

    Department department = entityManager.createQuery(query).getSingleResult();
    Set<Doctor> doctors = department.getDoctors();
    for(Doctor doctor : doctors){
      doctor.getDepartments().remove(department);
      department.getDoctors().remove(doctor);
    }

    entityManager.persist(department);
  }
}

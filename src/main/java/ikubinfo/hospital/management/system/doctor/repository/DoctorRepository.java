package ikubinfo.hospital.management.system.doctor.repository;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.doctor.entity.Doctor;
import ikubinfo.hospital.management.system.doctor.entity.DoctorDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DoctorRepository {

  private final EntityManager entityManager;

  public Doctor addDoctor(Doctor doctor) {
    return entityManager.merge(doctor);
  }

  public void registerDepartment(Doctor doctor) {
    entityManager.persist(doctor);
  }

  public Doctor findById(Long id) {
    return entityManager.find(Doctor.class, id);
  }

  public Integer deleteById(Long id) {
    // when I delete doctor I must delete its relations with department
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaDelete<Doctor> criteriaDelete = cb.createCriteriaDelete(Doctor.class);
    Root<Doctor> root = criteriaDelete.from(Doctor.class);
    criteriaDelete.where(cb.equal(root.get("id"), id));

    return entityManager.createQuery(criteriaDelete).getFirstResult();
  }

  public Integer deleteByDepartmentId(Long id) {
    // this is not necessary I should handle it on department service
    // I need to delete reference of doctors from department
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaDelete<Doctor> criteriaDelete = cb.createCriteriaDelete(Doctor.class);
    Root<Doctor> root = criteriaDelete.from(Doctor.class);
    Join<Doctor, Department> department = root.join("departments");
    criteriaDelete.where(cb.equal(department.get("id"), id));

    return entityManager.createQuery(criteriaDelete).getFirstResult();
  }

  public List<Doctor> getDoctor(Long id) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Doctor> query = cb.createQuery(Doctor.class);
    Root<Doctor> root = query.from(Doctor.class);
    root.fetch("departments", JoinType.LEFT);
    query.where(cb.equal(root.get("id"), id));

    return entityManager.createQuery(query).getResultList();
  }

  public void deleteDepartmentOfDoctor(Long doctorId, Long departmentId) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();

    CriteriaQuery<Doctor> query = cb.createQuery(Doctor.class);
    Root<Doctor> root = query.from(Doctor.class);
    Join<Doctor, Department> departmentJoin = root.join("departments");

    query.where(
        cb.and(
            cb.equal(root.get("id"), doctorId), cb.equal(departmentJoin.get("id"), departmentId)));

    Doctor doctor = entityManager.createQuery(query).getSingleResult();

    doctor
        .getDepartments()
        .removeIf(
            department -> {
              if (department.getId().equals(departmentId)) {
                department.getDoctors().remove(doctor);
                return true;
              } else return false;
            });

    entityManager.merge(doctor);
  }

  public void deleteDepartmentsOfDoctors(Long doctorId, List<Long> departmentsIds) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Doctor> query = cb.createQuery(Doctor.class);
    Root<Doctor> root = query.from(Doctor.class);
    Join<Doctor, Department> join = root.join("departments");
    query.where(cb.equal(root.get("id"), doctorId));

    Doctor doctor = entityManager.createQuery(query).getSingleResult();

    doctor
        .getDepartments()
        .removeIf(
            department -> {
              if (departmentsIds.contains(department.getId())) {
                department.getDoctors().remove(doctor);
                departmentsIds.remove(department.getId());
                return true;
              }
              return false;
            });

    entityManager.persist(doctor);
  }

  public void deleteAllDepartmentsOfDoctor(Long doctorId) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Doctor> query = cb.createQuery(Doctor.class);
    Root<Doctor> root = query.from(Doctor.class);
    Join<Doctor, Department> join = root.join("departments");
    query.where(cb.equal(root.get("id"), doctorId));

    Doctor doctor = entityManager.createQuery(query).getSingleResult();

    doctor
        .getDepartments()
        .forEach(
            department -> {
              department.getDoctors().remove(doctor);
              doctor.getDepartments().remove(department);
            });

    entityManager.persist(doctor);
  }
}

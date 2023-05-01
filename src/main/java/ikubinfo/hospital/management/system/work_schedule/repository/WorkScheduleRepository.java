package ikubinfo.hospital.management.system.work_schedule.repository;

import ikubinfo.hospital.management.system.work_schedule.entity.WorkSchedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class WorkScheduleRepository {

  private EntityManager entityManager;

  public WorkSchedule addWorkSchedule(WorkSchedule workSchedule) {
    return entityManager.merge(workSchedule);
  }

  public WorkSchedule getWorkSchedule(Long id) {
    return entityManager.find(WorkSchedule.class, id);
  }

  public Integer deleteWorkSchedulesByDoctorId(Long doctorId) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaDelete<WorkSchedule> criteriaDelete = cb.createCriteriaDelete(WorkSchedule.class);
    Root<WorkSchedule> root = criteriaDelete.from(WorkSchedule.class);
    root.fetch("doctor", JoinType.LEFT);
    criteriaDelete.where(cb.equal(root.get("doctor").get("id"), doctorId));

    return entityManager.createQuery(criteriaDelete).executeUpdate();
  }

  public Integer deleteAllWorkSchedulesOfDepartment(Long departmentId) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaDelete<WorkSchedule> criteriaDelete = cb.createCriteriaDelete(WorkSchedule.class);
    Root<WorkSchedule> root = criteriaDelete.from(WorkSchedule.class);
    root.fetch("department", JoinType.LEFT);
    criteriaDelete.where(cb.equal(root.get("department").get("id"), departmentId));

    return entityManager.createQuery(criteriaDelete).executeUpdate();
  }

  public void deleteWorkScheduleById(Long id) {
    WorkSchedule workSchedule = entityManager.getReference(WorkSchedule.class, id);
    entityManager.remove(workSchedule);
  }
}

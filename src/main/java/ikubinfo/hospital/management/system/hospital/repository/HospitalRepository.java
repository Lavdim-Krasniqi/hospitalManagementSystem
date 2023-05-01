package ikubinfo.hospital.management.system.hospital.repository;

import ikubinfo.hospital.management.system.hospital.entity.Hospital;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class HospitalRepository {

  private final EntityManager entityManager;

  @Transactional
  public Hospital addHospital(Hospital hospital) {
    return entityManager.merge(hospital);
  }

  public Hospital findHospitalById(Long id) {
    return entityManager.find(Hospital.class, id);
  }

  public Integer deleteById(Long id) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaDelete<Hospital> criteriaDelete = cb.createCriteriaDelete(Hospital.class);
    Root<Hospital> root = criteriaDelete.from(Hospital.class);

    criteriaDelete.where(cb.equal(root.get("id"), id));

    return entityManager.createQuery(criteriaDelete).executeUpdate();
  }
}

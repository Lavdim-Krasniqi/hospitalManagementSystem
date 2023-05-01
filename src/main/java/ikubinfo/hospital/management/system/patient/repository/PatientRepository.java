package ikubinfo.hospital.management.system.patient.repository;

import ikubinfo.hospital.management.system.patient.entity.Patient;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class PatientRepository {

  private final EntityManager entityManager;

  public Patient addPatient(Patient patient) {
    return entityManager.merge(patient);
  }
}

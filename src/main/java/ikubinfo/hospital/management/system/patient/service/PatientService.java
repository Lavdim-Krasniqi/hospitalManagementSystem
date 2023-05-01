package ikubinfo.hospital.management.system.patient.service;

import ikubinfo.hospital.management.system.patient.entity.Patient;
import ikubinfo.hospital.management.system.patient.entity.PatientDto;
import ikubinfo.hospital.management.system.patient.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientService {

  private final PatientRepository repo;

  public PatientDto addPatient(PatientDto patient) {
    return repo.addPatient(patient.getPatientFromDto()).getPatientDto();
  }
}

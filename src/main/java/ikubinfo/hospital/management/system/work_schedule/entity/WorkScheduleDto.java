package ikubinfo.hospital.management.system.work_schedule.entity;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.doctor.entity.Doctor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@AllArgsConstructor
@Data
@Builder
public class WorkScheduleDto {

    private Long id;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private Instant timeFrom;
    private Instant timeTo;
    private Long doctorId;
    private Long departmentId;

}

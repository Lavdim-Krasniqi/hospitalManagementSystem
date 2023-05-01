package ikubinfo.hospital.management.system.doctor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@AllArgsConstructor
@Data
public class DoctorDto {

    private Long id;

    private String name;
    private Long personalNumber;
    private String specialization;
    private Instant createdDate;
    private Instant lastModifiedDate;

}

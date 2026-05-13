package lk.ijse.serenitymentalhealththerapycenter.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RegistrationDTO {
    private String id;
    private PatientDTO patient;
    private LocalDate registerDate;
    private Set<SessionDTO> sessions;
}

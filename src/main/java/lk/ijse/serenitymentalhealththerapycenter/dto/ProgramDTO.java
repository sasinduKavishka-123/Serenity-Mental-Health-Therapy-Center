package lk.ijse.serenitymentalhealththerapycenter.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProgramDTO {
    private String id;
    private String name;
    private String duration;
    private BigDecimal fee;
    private Set<TherapistDTO> therapists;
}

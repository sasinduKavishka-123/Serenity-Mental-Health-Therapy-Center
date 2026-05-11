package lk.ijse.serenitymentalhealththerapycenter.dto;

import lombok.*;

import java.math.BigDecimal;

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
}

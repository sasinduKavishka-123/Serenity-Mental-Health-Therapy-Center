package lk.ijse.serenitymentalhealththerapycenter.dto;

import lombok.*;

// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TherapistDTO {
    private String id;
    private String name;
    private String contact;
    private String email;
}

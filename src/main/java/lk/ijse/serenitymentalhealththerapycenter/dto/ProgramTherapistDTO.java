package lk.ijse.serenitymentalhealththerapycenter.dto;


import lombok.*;

// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProgramTherapistDTO {
    private String prID;
    private String prName;
    private String tID;
    private String tName;
}

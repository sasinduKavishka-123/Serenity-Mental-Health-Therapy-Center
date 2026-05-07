package lk.ijse.serenitymentalhealththerapycenter.dto;

import lombok.*;

// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PatientDTO {
    private String id;
    private String name;
    private String gender;
    private String contact;
    private String address;
    private String registeredDay;
}

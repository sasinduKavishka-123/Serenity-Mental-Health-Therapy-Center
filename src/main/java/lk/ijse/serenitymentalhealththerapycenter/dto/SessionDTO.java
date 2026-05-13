package lk.ijse.serenitymentalhealththerapycenter.dto;


import lombok.*;

// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SessionDTO {
    private String id;
    private RegistrationDTO registration;
    private TherapistDTO therapist;
    private ProgramDTO program;
    private String day;
    private String time;
    private PaymentDTO payment;
}

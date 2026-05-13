package lk.ijse.serenitymentalhealththerapycenter.dto;


import lombok.*;

// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SessionTM {
    private String id;
    private String program;
    private String therapist;
    private String day;
    private String time;
}

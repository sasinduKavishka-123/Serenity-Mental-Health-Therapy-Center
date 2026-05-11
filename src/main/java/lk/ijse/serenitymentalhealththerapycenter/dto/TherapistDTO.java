package lk.ijse.serenitymentalhealththerapycenter.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

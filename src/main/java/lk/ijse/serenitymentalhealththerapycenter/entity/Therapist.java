package lk.ijse.serenitymentalhealththerapycenter.entity;

import jakarta.persistence.*;
import lombok.*;

// hibernate ////////////////
@Entity
@Table(name= "therapist")
// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Therapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="t_name")
    private String name;
    @Column(name="t_contact")
    private String contact;
    @Column(name="t_email")
    private String email;
}

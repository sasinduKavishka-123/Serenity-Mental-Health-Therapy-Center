package lk.ijse.serenitymentalhealththerapycenter.entity;


import jakarta.persistence.*;
import lombok.*;

// hibernate ////////////////
@Entity
@Table(name= "patient")
// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="p_name")
    private String name;
    private String gender;
    private String contact;
    private String address;
    private String registeredDay;
}

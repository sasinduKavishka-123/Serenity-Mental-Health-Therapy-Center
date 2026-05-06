package lk.ijse.serenitymentalhealththerapycenter.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

// hibernate ////////////////
@Entity
@Table(name= "patient")
// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Date registeredDay;
}

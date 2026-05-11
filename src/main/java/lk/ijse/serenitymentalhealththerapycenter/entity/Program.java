package lk.ijse.serenitymentalhealththerapycenter.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

// hibernate ////////////////
@Entity
@Table(name= "programs")
// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name="pro_name")
    private String name;
    @Column(name="pro_duration")
    private String duration;
    @Column(precision = 10, scale = 2 , name="pro_fee")
    private BigDecimal fee;

    @ManyToMany(mappedBy = "programs")
    private Set<Therapist> therapists = new HashSet<>();
}

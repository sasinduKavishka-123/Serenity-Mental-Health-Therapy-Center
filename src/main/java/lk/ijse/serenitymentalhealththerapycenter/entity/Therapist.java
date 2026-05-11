package lk.ijse.serenitymentalhealththerapycenter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "therapist_program",            // join table name
            joinColumns = @JoinColumn(name = "therapist_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private Set<Program> programs = new HashSet<>();


    // add and remove elements using hashset methods ------------------
    public void addProgram(Program program) {
        this.programs.add(program);
    }
    public void removeProgram(Program program) {
        this.programs.remove(program);
    }
}

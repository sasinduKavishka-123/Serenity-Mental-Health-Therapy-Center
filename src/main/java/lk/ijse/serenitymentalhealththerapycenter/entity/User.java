package lk.ijse.serenitymentalhealththerapycenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

// hibernate ////////////////
@Entity
@Table(name= "user")
// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    @Id
    private Long id;
}

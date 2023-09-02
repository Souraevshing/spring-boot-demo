package spring.todo.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")

// table roles will have id and name column, and we have inserted id and name of type USER and ADMIN with ids of USER and ADMIN with column name ROLE_USER for USER and ROLE_ADMIN for ADMIN.
// we map users and user_roles table by inserting ROLE_USER and ROLE_ADMIN for USER and ADMIN respectively.

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}

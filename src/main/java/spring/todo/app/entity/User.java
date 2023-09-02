package spring.todo.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // implement many-to-many relation between tables user_roles and users
    // fetch denotes to fetch or update users table whenever we get any data from user_roles so that there is no delay everytime we fetch data from db.
    // The EAGER strategy is a requirement on the persistence provider runtime that data must be eagerly fetched.
    // cascade denotes that which kind of operations to perform when firing and kind of query from db.
    // The cascade type ALL will perform all operations PERSIST MERGE REMOVE REFRESH DETACH.

    // JoinTable will join two tables user and user_roles and joinColumns will join column user_id of table user_roles with column id of table users.

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Role> roles;

}

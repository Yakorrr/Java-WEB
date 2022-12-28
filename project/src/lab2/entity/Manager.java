package lab2.entity;

import javax.persistence.*;

@Entity
public class Manager extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "user")
    @JoinTable(
            name = "manager",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Long idUser;

    public Manager(String firstName, String lastName, String telephoneNumber) {
        super(firstName, lastName, telephoneNumber);
    }
}

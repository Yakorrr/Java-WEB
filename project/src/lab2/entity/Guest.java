package lab2.entity;

import javax.persistence.*;
import java.util.Comparator;

@Entity
public class Guest extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "user")
    @JoinTable(
            name = "guest",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Long idUser;

    private boolean child;

    public Guest(String firstName, String lastName, String telephoneNumber, boolean child) {
        super(firstName, lastName, telephoneNumber);
        this.child = child;
    }

    public boolean isChild() {
        return child;
    }

    public void setChild(boolean child) {
        this.child = child;
    }

    /**
     * @return A {@code Comparator} that orders adults first then by firstName then lastName.
     */
    public static Comparator<Guest> comparator() {
        return Comparator.comparing(Guest::isChild, Boolean::compareTo)
                .thenComparing(Guest::getFirstName)
                .thenComparing(Guest::getLastName);
    }
}

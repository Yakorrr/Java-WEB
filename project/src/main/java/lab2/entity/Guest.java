package lab2.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;

@Entity
@Table(name = "guests")
public class Guest extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private boolean child;

    @OneToOne(mappedBy = "guests")
    private Person person;

    public Guest(String firstName, String lastName, 
                 LocalDate dateOfBirth, String gender, 
                 String telephoneNumber, boolean child) {
        super(firstName, lastName, dateOfBirth, gender, telephoneNumber);
        this.child = child;
    }

    public Guest() {
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

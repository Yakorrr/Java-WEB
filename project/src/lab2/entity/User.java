package lab2.entity;

import lab2.util.Utils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity
public class User {
    @Transient
    private final UUID tempId = UUID.randomUUID();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 20)
    @NotNull(message = "required")
    @Column(nullable = false)
    private String firstName;

    @Size(min = 2, max = 20)
    @NotNull(message = "required")
    @Column(nullable = false)
    private String lastName;

    @NotEmpty
    @NotNull(message = "required")
    @Pattern(regexp = "^([+]?[0-9]{1,3}[- ]?)?(\\([0-9]{1,3}\\)|" +
            "[0-9]{1,3})[- ]?([0-9]{3}[- ]?[0-9]{2}[- ]?[0-9]{2})",
            message = "Telephone number expected")
    private String telephoneNumber;

    public User(String firstName, String lastName, String telephoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        this.telephoneNumber = telephoneNumber;
    }

    public UUID getTempId() {
        return tempId;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * Converts to lowercase for consistent equality checks
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName.toLowerCase();
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Converts to lowercase for consistent equality checks
     */
    public void setLastName(String lastName) {
        this.lastName = lastName.toLowerCase();
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFormattedFullName() {
        return Utils.capitalizeWords(firstName) + " " + Utils.capitalizeWords(lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, telephoneNumber);
    }

    /**
     * Cant use a UUID because that does not prevent a guest with the same name being added.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;

        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(telephoneNumber, user.telephoneNumber);
    }

    @Override
    public String toString() {
        return getFormattedFullName() + ", " + telephoneNumber;
    }
}

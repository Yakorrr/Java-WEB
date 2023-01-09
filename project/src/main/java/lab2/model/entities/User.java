package lab2.model.entities;

import lombok.*;
import org.apache.log4j.Logger;
import lab2.model.enums.Language;
import lab2.model.enums.Role;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private static final Logger logger = Logger.getLogger(Room.class);


    @Getter
    @Setter
    private int id;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private LocalDate dateOfBirth;
    @Getter
    private String gender;
    @Getter
    private String telephoneNumber;
    @Getter
    private String email;
    @Getter
    private Role role;
    @Getter
    private Language language;
    @Getter
    private String passwordEncoded;

    public User(String firstName, String lastName, LocalDate dateOfBirth, String gender,
                String telephoneNumber, String email,
                Role role, Language language, String passwordEncoded) {
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setTelephoneNumber(telephoneNumber);
        setEmail(email);
        setRole(role);
        setLanguage(language);
        setPasswordEncoded(passwordEncoded);

        logger.info("Object User successfully created");
    }

    public void setFirstName(String name) {
        if (name != null) {
            this.firstName = name;
        } else {
            logger.error("Error: 'firstName' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setLastName(String name) {
        if (name != null) {
            this.lastName = name;
        } else {
            logger.error("Error: 'lastName' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth != null) {
            this.dateOfBirth = dateOfBirth;
        } else {
            logger.error("Error: 'dateOfBirth' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setGender(String gender) {
        if (gender != null) {
            this.gender = gender;
        } else {
            logger.error("Error: 'gender' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setTelephoneNumber(String telephoneNumber) {
        if (telephoneNumber != null) {
            this.telephoneNumber = telephoneNumber;
        } else {
            logger.error("Error: 'telephoneNumber' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        } else {
            logger.error("Error: 'email' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setRole(Role role) {
        if (role != null) {
            this.role = role;
        } else {
            logger.error("Error: 'role' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setLanguage(Language language) {
        if (language != null) {
            this.language = language;
        } else {
            logger.error("Error: 'language' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setPasswordEncoded(String passwordEncoded) {
        if (passwordEncoded != null) {
            this.passwordEncoded = passwordEncoded;
        } else {
            logger.error("Error: 'passwordEncoded' object can not be null");
            throw new NullPointerException();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;

        return id == user.id && firstName.equals(user.firstName)
                && lastName.equals(user.lastName)
                && dateOfBirth.equals(user.dateOfBirth)
                && gender.equals(user.gender)
                && telephoneNumber.equals(user.telephoneNumber)
                && email.equals(user.email)
                && role.equals(user.role)
                && language.equals(user.language);
    }
}


package lab2.entity;

import lab2.model.AES;
import lab2.util.Utils;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "persons")
public class Person {
	@Transient
	private final UUID tempId = UUID.randomUUID();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Size(min = 1)
	@NotNull(message = "required")
	@Column(nullable = false)
	private String firstName;

	@Size(min = 1)
	@NotNull(message = "required")
	@Column(nullable = false)
	private String lastName;

	@NotEmpty
	@NotNull(message = "required")
	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@NotEmpty
	@NotNull(message = "required")
	@Column(nullable = false)
	private String gender;

	@NotEmpty
	@NotNull(message = "required")
	@Pattern(regexp = "^([+]?[0-9]{1,3}[- ]?)?(\\([0-9]{1,3}\\)|" +
			"[0-9]{1,3})[- ]?([0-9]{3}[- ]?[0-9]{2}[- ]?[0-9]{2})",
			message = "Telephone number expected")
	private String telephoneNumber;

	@NotEmpty
	@NotNull(message = "required")
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
			message = "Email expected")
	private String email;

	@NotEmpty(message = "Password cannot be empty")
	@NotNull(message = "required")
	@Column(nullable = false)
	private String password;

	@Formula(value = "Period.between(this.dateOfBirth, LocalDate.now()).getYears()")
	private Integer age;

	private final String secretKey = "abcsecretkey";

	public Person(String firstName, String lastName, LocalDate dateOfBirth,
				  String gender, String telephoneNumber,
				  String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.telephoneNumber = telephoneNumber;
		this.email = email;
		this.password = new AES().encrypt(password, secretKey);
		this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
	}

	public Person(String firstName, String lastName, LocalDate dateOfBirth, String gender, String telephoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.telephoneNumber = telephoneNumber;
	}

	public Person() {

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

	public Long getId() {
		return id;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new AES().encrypt(password, secretKey);
	}

	public Integer getAge() {
		return age;
	}

	public String getFormattedFullName() {
		return Utils.capitalizeWords(firstName) + " " + Utils.capitalizeWords(lastName);
	}

	public UUID getTempId() {
		return tempId;
	}

	public String decryptPassword(String password) {
		return new AES().decrypt(password, secretKey);
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

		Person person = (Person) obj;

		return Objects.equals(firstName, person.firstName) &&
				Objects.equals(lastName, person.lastName);
	}

	@Override
	public String toString() {
		return getFormattedFullName();
	}
}

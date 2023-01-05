package lab2.dao;

import lab2.entity.Person;
import lab2.model.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class RegisterDao {
    String query = "insert into persons " +
            "(first_name, last_name, date_of_birth, gender, telephone_number, email, user_password)" +
            " values (?, ?, ?, ?, ?, ?, ?);";
    public int registerUser(Person person) {
        int result = 0;

        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        LocalDate dateOfBirth = person.getDateOfBirth();
        String gender = person.getGender();
        String telephoneNumber = person.getTelephoneNumber();
        String password = person.getPassword();

        try (Connection connection = DBConnection.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDate(3, Date.valueOf(dateOfBirth));
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, telephoneNumber);
            preparedStatement.setString(6, password);

            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

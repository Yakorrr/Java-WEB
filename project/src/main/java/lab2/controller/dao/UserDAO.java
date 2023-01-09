package lab2.controller.dao;

import com.sun.istack.internal.NotNull;
import lab2.model.enums.Language;
import lab2.model.enums.Role;
import lab2.model.entities.User;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserDAO extends AbstractDAO<User> {
    private static final Logger logger = Logger.getLogger(UserDAO.class);

    private static final String INSERT = "INSERT INTO users" +
            " (first_name, last_name, date_of_birth, gender, telephone_number, email," +
            " role, language, pass_encoded) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_BY_ID = "SELECT * FROM users " +
            "WHERE id = ?;";
    private static final String SELECT_ALL = "select * from users;";
    private static final String DELETE = "DELETE FROM users " +
            "WHERE id = ?;";
    private static final String UPDATE = "UPDATE users SET first_name=? AND last_name=?" +
            " AND date_of_birth=? AND gender=? AND telephone_number=? AND email=? " +
            "AND role=? AND language=? AND pass_encoded=? " +
            "WHERE id = ?;";
    private static final String FIND = "SELECT id FROM users " +
            "WHERE (first_name=? AND last_name=? AND date_of_birth=? AND gender=? " +
            "AND telephone_number=? AND email=? AND role=? AND language=? AND pass_encoded=?);";

    Logger getLogger() {
        return logger;
    }

    String getInsert() {
        return INSERT;
    }

    String getSelectById() {
        return SELECT_BY_ID;
    }

    public String getSelectAll() {
        return SELECT_ALL;
    }

    String getDelete() {
        return DELETE;
    }

    String getUpdate() {
        return UPDATE;
    }

    String getFind() {
        return FIND;
    }

    @Override
    void setStatement(@NotNull PreparedStatement preparedStatement, @NotNull User object) throws SQLException {
        preparedStatement.setString(1, object.getFirstName());
        preparedStatement.setString(2, object.getLastName());
        preparedStatement.setDate(3, Date.valueOf(object.getDateOfBirth()));
        preparedStatement.setString(4, object.getGender());
        preparedStatement.setString(5, object.getTelephoneNumber());
        preparedStatement.setString(6, object.getEmail());
        preparedStatement.setString(7, object.getRole().toString());
        preparedStatement.setString(8, object.getLanguage().toString());
        preparedStatement.setString(9, object.getPasswordEncoded());
    }

    @Override
    void setUpdateStatementId(@NotNull PreparedStatement preparedStatement, @NotNull User object) throws SQLException {
        preparedStatement.setInt(10, object.getId());
    }

    @Override
    User setObjectParams(@NotNull ResultSet rs) throws SQLException {
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String dateOfBirth = rs.getString("date_of_birth");
        String gender = rs.getString("gender");
        String telephoneNumber = rs.getString("telephone_number");
        String email = rs.getString("email");
        Role role = Role.valueOf(rs.getString("role"));
        Language language = Language.valueOf(rs.getString("language"));
        String password = rs.getString("pass_encoded");

        User object = new User();

        object.setFirstName(firstName);
        object.setLastName(lastName);
        object.setDateOfBirth(LocalDate.parse(dateOfBirth));
        object.setGender(gender);
        object.setTelephoneNumber(telephoneNumber);
        object.setEmail(email);
        object.setRole(role);
        object.setLanguage(language);
        object.setPasswordEncoded(password);

        return object;
    }

    @Override
    void setObjectId(@NotNull ResultSet rs, @NotNull User object) throws SQLException {
        int id = rs.getInt("id");
        object.setId(id);
    }
}
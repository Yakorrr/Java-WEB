package lab2.controller.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCVars {
    private static final Logger logger = Logger.getLogger(JDBCVars.class);

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/hotel?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "ba3h!UN6A6M#@6k";

    static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }

        logger.info("Connecting to DB: success");

        return connection;
    }

    private JDBCVars() {

    }
}

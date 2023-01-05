package lab2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/javalabs?useSSL=false";
    private static final String admin = "root";
    private static final String password = "ba3h!UN6A6M#@6k";

    public static Connection createConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, admin, password);
        } catch (SQLException e) {
            printSQLException(e);
        }

        return con;
    }

    private static void printSQLException(SQLException exception) {
        for (Throwable e : exception) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);

                System.err.print("SQL State: " + ((SQLException) e).getSQLState());
                System.err.print("Error code: " + ((SQLException) e).getErrorCode());
                System.err.print("Message: " + e.getMessage());

                Throwable t = exception.getCause();

                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

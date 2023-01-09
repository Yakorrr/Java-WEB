package lab2.controller.dao;

import org.apache.log4j.Logger;
import lab2.model.pojo.Bill;
import lab2.model.pojo.Request;
import lab2.model.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lab2.controller.dao.JDBCVars.getConnection;

/**
 * TODO: all proxy - change to real DB requests
 * TODO: all proxy - write tests
 */
public class BillDAOProxy extends BillDAO {
    private static final Logger logger = Logger.getLogger(BillDAOProxy.class);

    private static final String FIND_BY_REQUEST = "SELECT * from bills " +
            "WHERE requests_id = ?";

    public Bill findBillByRequestId(int id) {
        Bill object = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_REQUEST)) {

            preparedStatement.setInt(1, id);
            logger.info("Executing statement: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                object = setObjectParams(rs);
                setObjectId(rs, object);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Select by request id: success");
        return object;
    }

    public User findBillUser(int id) {
        Request req = selectById(id).getRequest();
        return req.getUser();
    }
}

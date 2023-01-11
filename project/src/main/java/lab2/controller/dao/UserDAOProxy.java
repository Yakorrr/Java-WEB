package lab2.controller.dao;

import com.sun.istack.internal.NotNull;
import lab2.model.entities.Bill;
import lab2.model.entities.Request;
import org.apache.log4j.Logger;
import lab2.model.enums.Role;
import lab2.model.entities.User;

import java.util.List;
import java.util.Optional;

import static lab2.controller.util.StringConverter.strToInt;

public class UserDAOProxy extends UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAOProxy.class);

    public void delete(@NotNull String id) {
        Optional<Integer> intId = strToInt(id);

        if (intId.isPresent()) {
            RequestDAO requestDAO = new RequestDAO();
            BillDAOProxy billDAOProxy = new BillDAOProxy();

            List<Request> requests = requestDAO.find(intId.get());

            if (requests != null) {
                for (Request r : requests) {
                    Bill bill = billDAOProxy.findBillByRequestId(r.getId());

                    if (bill != null) {
                        billDAOProxy.delete(bill.getId());
                        logger.info("Bill with id " + bill.getId() + " has been successfully deleted");
                    }

                    requestDAO.delete(r.getId());
                    logger.info("Request with id " + r.getId() + " has been successfully deleted");
                }
            }

            delete(intId.get());
            logger.info("User object with id " + id + " has been successfully deleted");
        } else {
            logger.error("User object with id " + id + " hasn't been deleted. Wrong id");
        }
    }

    public void toAdmin(@NotNull String id) {
        Optional<Integer> intId = strToInt(id);
        UserDAO userDAO = new UserDAO();

        if (intId.isPresent()) {
            User object = userDAO.selectById(intId.get());
            object.setRole(Role.ADMIN);
            userDAO.update(object);
            logger.info("User object with id " + id + " has been granted ADMIN rights successfully");
        } else {
            logger.error("User object with id " + id + " hasn't been promoted to ADMIN. Wrong id");
        }
    }

    public void toUser(@NotNull String id) {
        Optional<Integer> intId = strToInt(id);

        if (intId.isPresent()) {
            User object = selectById(intId.get());
            object.setRole(Role.USER);
            update(object);
            logger.info("User object with id " + id + " has been granted ADMIN rights successfully");
        } else {
            logger.error("User object with id " + id + " hasn't been promoted to ADMIN. Wrong id");
        }
    }
}

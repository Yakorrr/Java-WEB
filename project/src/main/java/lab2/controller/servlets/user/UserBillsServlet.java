package lab2.controller.servlets.user;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import lab2.controller.dao.BillDAOProxy;
import lab2.controller.util.Pagination;
import lab2.model.entities.Bill;
import lab2.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/user-my-bills")
public class UserBillsServlet extends HttpServlet {
    private final BillDAOProxy billDAOProxy = new BillDAOProxy();
    private final Pagination<Bill> pagination = new Pagination<>();
    private static final Logger logger = Logger.getLogger("UserBillsServlet");

    private void payTheBill(@NotNull Integer id, @NotNull HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("user") == null) {
            logger.error("Unauthorized payment: impossible to pay the bill");
            return;
        }

        User u = (User) session.getAttribute("user");
        User billUser = billDAOProxy.findBillUser(id);

        if (billUser.equals(u)) {
            Bill toPay = billDAOProxy.selectById(id);
            toPay.setPaid(true);
            billDAOProxy.update(toPay);
            logger.info("Bill payed successfully");
        } else {
            logger.error("Unauthorized payment: impossible to pay the bill");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");

        if (method != null && id != null) {
            if (method.equals("pay")) {
                payTheBill(Integer.parseInt(id), request);
            }
        }

        User currentUser = (User) request.getSession().getAttribute("user");
        List<Bill> billsToDisplay = billDAOProxy.selectAll().stream()
                .filter(b -> b.getRequest().getUser().getId() == currentUser.getId())
                .collect(Collectors.toList());
        pagination.paginate(billsToDisplay, request);
        request.getRequestDispatcher("templates/user/user-bills.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

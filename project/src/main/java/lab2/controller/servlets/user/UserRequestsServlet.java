package lab2.controller.servlets.user;

import com.sun.istack.internal.NotNull;
import lab2.controller.dao.BillDAOProxy;
import lab2.controller.dao.RequestDAO;
import lab2.controller.util.Pagination;
import lab2.model.entities.Bill;
import lab2.model.entities.Request;
import lab2.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/user-my-requests")
public class UserRequestsServlet extends HttpServlet {
    private final RequestDAO requestDAO = new RequestDAO();
    private final BillDAOProxy billDAOProxy = new BillDAOProxy();
    private final Pagination<Request> pagination = new Pagination<>();

    /**
     * Deletes request from database, even if related bill exists and was paid.
     * If the bill exists, deletes it too.
     *
     * @param id - id of the request to be deleted
     */
    private void cancelRequest(@NotNull int id) {
        Bill relatedBill = billDAOProxy.findBillByRequestId(id);

        if (relatedBill != null) {
            billDAOProxy.delete(relatedBill.getId());
        }

        requestDAO.delete(id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");

        if (method != null && id != null) {
            if (method.equals("cancel")) {
                cancelRequest(Integer.parseInt(id));
            }
        }

        User currentUser = (User) request.getSession().getAttribute("user");
        List<Request> requestsToDisplay = requestDAO.selectAll().stream()
                .filter(r -> r.getUser().getId() == currentUser.getId())
                .collect(Collectors.toList());

        pagination.paginate(requestsToDisplay, request);
        request.getRequestDispatcher("templates/user/user-requests.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

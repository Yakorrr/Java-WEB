package lab2.controller.servlets.admin;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import lab2.controller.dao.*;
import lab2.controller.util.Pagination;
import lab2.controller.util.PasswordEncoder;
import lab2.model.enums.Language;
import lab2.model.enums.Role;
import lab2.model.entities.Bill;
import lab2.model.entities.Request;
import lab2.model.entities.Room;
import lab2.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/admin", "/admin-users", "/admin-tables", "/admin-update", "/admin-users-update"})
public class AdminServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AdminServlet.class);

    private final Pagination<Request> requestPagination = new Pagination<>();
    private final Pagination<User> userPagination = new Pagination<>();
    private final Pagination<Room> roomPagination = new Pagination<>();
    private final Pagination<Bill> billPagination = new Pagination<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/admin":
                doGet(request, response);
                break;
            case "/admin-update":
                showAdminPage(request, response);
                break;
            case "/admin-users-update":
                String firstName = request.getParameter("first-name");
                String lastName = request.getParameter("last-name");
                String dateOfBirth = request.getParameter("date-of-birth");
                String gender = request.getParameter("gender");
                String telephoneNumber = request.getParameter("telephone-number");
                String email = request.getParameter("email");
                Role role = Role.valueOf(request.getParameter("role"));
                Language language = Language.valueOf(request.getParameter("lang"));
                String password = PasswordEncoder.getSHA(request.getParameter("password"));

                new UserDAO().insert(new User(firstName, lastName, LocalDate.parse(dateOfBirth),
                        gender, telephoneNumber, email, role, language, password));
                showUserTable(request, response);
                break;
            default:
                showNewForm(request, response, "success_admin.jsp");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/admin":
                processAdminRequest(request, response);
                break;
            case "/admin-users":
                processUserManagementPageRequest(request, response);
                break;
            case "/admin-tables":
                billPagination.paginate("-bills", new BillDAO().selectAll(), request);
                roomPagination.paginate("-rooms", new RoomDAO().selectAll(), request);
                showNewForm(request, response, "tables.jsp");
                break;
            case "/admin-stats":
                showNewForm(request, response, "statistics.jsp");
                break;
            default:
                showNewForm(request, response, "success_admin.jsp");
                break;
        }
    }

    private void showNewForm(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull String filename)
            throws ServletException, IOException {
        String templatePath = "templates/admin/";
        request.getRequestDispatcher(templatePath + filename).forward(request, response);
    }

    private void showAdminPage(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        List<Request> requests = new RequestDAO().selectAll().stream()
                .filter(r -> !r.isApproved())
                .collect(Collectors.toList());

        requestPagination.paginate(requests, request);
        showNewForm(request, response, "success_admin.jsp");
    }

    private void forwardToServlet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/approve").forward(request, response);
    }

    private void processAdminRequest(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");
        boolean invalid = false;

        if (method != null && id != null && method.equals("approve")) {
            try {
                int num = Integer.parseInt(id);
                request.setAttribute("req-id", num);
                forwardToServlet(request, response);
            } catch (NumberFormatException e) {
                invalid = true;
                logger.error(e.getMessage());
            }

            if (!invalid) {
                return;
            }

        }

        showAdminPage(request, response);
    }

    private void processUserManagementPageRequest(@NotNull HttpServletRequest request,
                                                  @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");
        UserDAOProxy proxyDao = new UserDAOProxy();

        if (method != null && id != null) {
            switch (method) {
                case "remove":
                    proxyDao.delete(id);
                    break;
                case "privilege_a":
                    proxyDao.toAdmin(id);
                    break;
                case "privilege_u":
                    proxyDao.toUser(id);
                    break;
            }
        }

        showUserTable(request, response);
    }

    private void showUserTable(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        List<User> userList = new UserDAO().selectAll();
        userPagination.paginate(userList, request);

        showNewForm(request, response, "users_management.jsp");
    }
}

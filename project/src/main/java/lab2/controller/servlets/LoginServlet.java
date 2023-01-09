package lab2.controller.servlets;

import lab2.controller.dao.UserDAO;
import lab2.controller.util.Localization;
import lab2.controller.util.PasswordEncoder;
import lab2.controller.util.Paths;
import lab2.controller.util.StringConverter;
import lab2.model.enums.Role;
import lab2.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(Paths.LOGIN.getUrl()).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        try {
            email = StringConverter.decodeParameter(email);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();

        if (email.equals("")) {
            request.getServletContext().setAttribute("errMessage", "Invalid Email!");
            request.getRequestDispatcher("templates/login.jsp").forward(request, response);
        } else if (password.equals("")) {
            request.getServletContext().setAttribute("errMessage", "Invalid Password!");
            request.getRequestDispatcher("templates/login.jsp").forward(request, response);
        } else {
            List<User> users = userDAO.selectAll();
            User u = null;

            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    u = user;
                    break;
                }
            }

            try {
                if (u == null) {
                    request.getServletContext().setAttribute("errMessage",
                            "Incorrect data: user was not found!");
                    request.getRequestDispatcher("templates/login.jsp").forward(request, response);
                } else if (!Objects.requireNonNull(u)
                        .getPasswordEncoded().equals(PasswordEncoder.getSHA(password))) {
                    request.getServletContext().setAttribute("errMessage",
                            "Incorrect password. Please try again!");
                    request.getRequestDispatcher("templates/login.jsp").forward(request, response);
                } else if (u.getPasswordEncoded().equals(PasswordEncoder.getSHA(password))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", u);
                    Localization.setCurrentLanguage(u.getLanguage());

                    if (u.getRole() == Role.USER) {
                        request.getRequestDispatcher("templates/user/user-main.jsp").forward(request, response);
                    } else if (u.getRole() == Role.ADMIN) {
                        request.getRequestDispatcher("admin").forward(request, response);
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}

package lab2.controller.servlets;

import org.apache.log4j.Logger;
import lab2.model.enums.Role;
import lab2.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MainServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Role role = null;

        try {
            HttpSession currentSession = request.getSession();
            User currentUser = (User) currentSession.getAttribute("user");
            role = currentUser.getRole();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        request.setAttribute("currentRole", role);
        logger.info("Entering MainServlet doGet method");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
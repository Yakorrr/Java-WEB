package lab2.controller.servlets;

import lab2.controller.dao.UserDAO;
import lab2.controller.util.Localization;
import lab2.controller.util.PasswordEncoder;
import lab2.controller.util.StringConverter;
import lab2.model.entities.User;
import lab2.model.enums.Gender;
import lab2.model.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static lab2.model.enums.Paths.LOGIN;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("templates/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String dateOfBirth = request.getParameter("date-of-birth");
        String gender = request.getParameter("gender");
        String telephoneNumber = request.getParameter("telephone-number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");

        User newUser = new User();
        UserDAO userDAO = new UserDAO();

        if (firstName.equals("")) {
            request.getServletContext().setAttribute("errMessage", "Invalid First Name!");
            request.getRequestDispatcher("templates/register.jsp").forward(request, response);
        } else if (lastName.equals("")) {
            request.getServletContext().setAttribute("errMessage", "Invalid Last Name!");
            request.getRequestDispatcher("templates/register.jsp").forward(request, response);
        } else if (dateOfBirth.equals("")) {
            request.getServletContext().setAttribute("errMessage", "Invalid Date of Birth!");
            request.getRequestDispatcher("templates/register.jsp").forward(request, response);
        } else if (Objects.equals(gender, null)) {
            request.getServletContext().setAttribute("errMessage", "Invalid Gender!");
            request.getRequestDispatcher("templates/register.jsp").forward(request, response);
        } else if (telephoneNumber.equals("")) {
            request.getServletContext().setAttribute("errMessage", "Invalid Telephone Number!");
            request.getRequestDispatcher("templates/register.jsp").forward(request, response);
        } else if (email.equals("")) {
            request.getServletContext().setAttribute("errMessage", "Invalid Email!");
            request.getRequestDispatcher("templates/register.jsp").forward(request, response);
        } else if (password.equals("") | confirmPassword.equals("")) {
            request.getServletContext().setAttribute("errMessage", "Invalid Password!");
            request.getRequestDispatcher("templates/register.jsp").forward(request, response);
        } else if (!password.equals(confirmPassword)) {
            request.getServletContext().setAttribute("errMessage", "Passwords do not match!");
            request.getRequestDispatcher("templates/register.jsp").forward(request, response);
        } else {
            List<User> users = userDAO.selectAll();
            User u = null;

            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    u = user;
                    break;
                }
            }

            if (u != null) {
                request.getServletContext().setAttribute("errMessage", "This user already exists!");
                request.getRequestDispatcher("templates/register.jsp").forward(request, response);
            }

            request.setCharacterEncoding("UTF-8");
            newUser.setFirstName(StringConverter.decodeParameter(firstName));
            newUser.setLastName(StringConverter.decodeParameter(lastName));
            newUser.setDateOfBirth(LocalDate.parse(StringConverter.decodeParameter(dateOfBirth)));
            newUser.setGender(Gender.valueOf(StringConverter.decodeParameter(gender)));
            newUser.setTelephoneNumber(StringConverter.decodeParameter(telephoneNumber));
            newUser.setEmail(StringConverter.decodeParameter(email));
            newUser.setRole(Role.USER);
            newUser.setLanguage(Localization.getCurrentLanguage());
            newUser.setPasswordEncoded(PasswordEncoder.getSHA(password));

            userDAO.insert(newUser);
            request.getRequestDispatcher(LOGIN.getUrl()).forward(request, response);
        }
    }
}

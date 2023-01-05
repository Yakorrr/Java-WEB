package lab2.controller;

import lab2.dao.RegisterDao;
import lab2.entity.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("date-of-birth"));
        String gender = request.getParameter("gender");
        String telephoneNumber = request.getParameter("telephone-number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setDateOfBirth(dateOfBirth);
        person.setGender(gender);
        person.setTelephoneNumber(telephoneNumber);
        person.setEmail(email);
        person.setPassword(password);

        int userRegistered = 0;

        try {
            RegisterDao registerDao = new RegisterDao();
            userRegistered = registerDao.registerUser(person);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userRegistered != 0)
            response.sendRedirect("index.jsp");
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
        else {
            request.setAttribute("errMessage", userRegistered);
            response.sendRedirect("register.jsp");
//            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}

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

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String dateOfBirth = request.getParameter("date-of-birth");
		String gender = request.getParameter("gender");
		String telephoneNumber = request.getParameter("telephone-number");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm-password");

		Person person = new Person();

		try {
			if (firstName.equals("")) {
//				System.out.println("Invalid name");
				request.setAttribute("errMessage", "Invalid First Name!");
//				System.out.println(request.getAttribute("errMessage"));
//				System.out.println(request.getParameter("first-name"));
				response.sendRedirect("register.jsp");
//				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else if (lastName.equals("")) {
				request.setAttribute("errMessage", "Invalid Last Name!");
				response.sendRedirect("register.jsp");
//				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else if (gender.equals("")) {
				request.setAttribute("errMessage", "Invalid Gender!");
				response.sendRedirect("register.jsp");
//				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else if (telephoneNumber.equals("")) {
				request.setAttribute("errMessage", "Invalid Telephone Number!");
				response.sendRedirect("register.jsp");
//				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else if (email.equals("")) {
				request.setAttribute("errMessage", "Invalid Email!");
				response.sendRedirect("register.jsp");
//				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else if (password.equals("") | confirmPassword.equals("")) {
				request.setAttribute("errMessage", "Invalid Password!");
				response.sendRedirect("register.jsp");
//				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else if (!password.equals(confirmPassword)) {
				request.setAttribute("errMessage", "Passwords does not match!");
				response.sendRedirect("register.jsp");
//				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else {
				person.setFirstName(firstName);
				person.setLastName(lastName);
				person.setDateOfBirth(LocalDate.parse(dateOfBirth));
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
					response.sendRedirect("successRegistration.jsp");
				else {
					request.setAttribute("errMessage", userRegistered);
					response.sendRedirect("register.jsp");
				}
			}
		} catch (Exception e) {
//			response.sendRedirect("register.jsp");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
}

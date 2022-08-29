package com.arunava.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.eclipse.jdt.internal.compiler.env.AccessRule;

import com.arunava.DAO.userDao;
import com.arunava.entity.Student;

@WebServlet("/userController")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private userDao userDao;

	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			userDao = new userDao(dataSource);
		} catch (Exception e) {
			throw new ServletException();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String theCommand = request.getParameter("command");
			if (theCommand == null) {
				listUser(request, response);
			}
			switch (theCommand) {
			case "LIST":
				listUser(request, response);
				break;
			case "LOAD":
				loadStudent(request, response);
				break;
			case "DELETE":
				deleteUser(request, response);
				break;

			default:
				listUser(request, response);
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");
			if (theCommand == null) {
				listUser(request, response);
			}
			switch (theCommand) {
			case "ADD":
				addUser(request, response);
				break;
			case "UPDATE":
				updateUser(request, response);
				break;

			default:
				listUser(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String theStudentId = request.getParameter("studentId");

		userDao.deleteStudent(theStudentId);

		listUser(request, response);

	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Student> students = userDao.getStudents();
		request.setAttribute("STUDENT_LIST", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);

	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String email = request.getParameter("email");

		Student theStudent = new Student(id, firstName, lastName, email);
		userDao.updateStudent(theStudent);

		response.sendRedirect("/UserDatabase/userController");

	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String theStudentId = request.getParameter("studentId");

		Student theStudent = userDao.getStudent(theStudentId);

		request.setAttribute("THE_STUDENT", theStudent);

		RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
		dispatcher.forward(request, response);

	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String email = request.getParameter("email");

		Student student = new Student(firstName, lastName, email);

		userDao.addUser(student);

		response.sendRedirect("/UserDatabase/userController");

	}

}

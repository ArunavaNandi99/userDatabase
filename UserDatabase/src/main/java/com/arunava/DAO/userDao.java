package com.arunava.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.arunava.entity.Student;

public class userDao {

	private DataSource dataSource;

	public userDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "select * from student";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstname = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");

				Student student = new Student(id, firstname, lastName, email);
				students.add(student);
			}

			return students;
		} finally {
			close(stmt, con, rs);
		}

	}

	private void close(Statement stmt, Connection con, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addUser(Student student) throws Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = dataSource.getConnection();
			String sql = "insert into student(first_name,last_name,email) value(?,?,?)";
			pst = con.prepareStatement(sql);

			pst.setString(1, student.getFirstName());
			pst.setString(2, student.getLastName());
			pst.setString(3, student.getEmail());

			pst.execute();

		} finally {
			close(pst, con, null);
		}

	}

	public Student getStudent(String theStudentId) throws Exception {

		Student theStudent = null;

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int studentId;

		try {

			studentId = Integer.parseInt(theStudentId);
			con = dataSource.getConnection();
			String sql = "select * from student where id =?";
			pst = con.prepareStatement(sql);

			pst.setInt(1, studentId);

			rs = pst.executeQuery();

			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");

				theStudent = new Student(studentId, firstName, lastName, email);
			} else {
				throw new Exception("Could not find student id :" + studentId);
			}

			return theStudent;

		} finally {
			close(pst, con, rs);
		}

	}

	public void updateStudent(Student theStudent) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = dataSource.getConnection();
			String sql = "update student set first_name=?, last_name=? ,email=? where id=?";
			pst = con.prepareStatement(sql);

			pst.setString(1, theStudent.getFirstName());
			pst.setString(2, theStudent.getLastName());
			pst.setString(3, theStudent.getEmail());
			pst.setInt(4, theStudent.getId());

			pst.execute();

		} finally {
			close(pst, con, null);
		}

	}

	public void deleteStudent(String theStudentId) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			int studentId = Integer.parseInt(theStudentId);
			con = dataSource.getConnection();
			String sql = "delete from student where id=? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1, studentId);
			pst.execute();
		} finally {
			close(pst, con, null);
		}

	}

}

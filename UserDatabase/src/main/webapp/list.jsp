<%@page import="com.arunava.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List students</title>
</head>

<body>
	<h2>The User</h2>

	<input type="button" value="Add User"
		onclick="window.location.href='add.jsp'; return false;">
	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Action</th>
		</tr>
		<c:forEach var="tempStudent" items="${STUDENT_LIST }">
			<c:url var="tempLink" value="userController">
				<c:param name="command" value="LOAD" />
				<c:param name="studentId" value="${tempStudent.id}"></c:param>
			</c:url>

			<c:url var="deleteLink" value="userController">
				<c:param name="command" value="DELETE" />
				<c:param name="studentId" value="${tempStudent.id}"></c:param>
			</c:url>

			<tr>
				<td>${tempStudent.firstName }</td>
				<td>${tempStudent.lastName }</td>
				<td>${tempStudent.email}</td>
				<td><a href="${tempLink}">Update</a> 
				| 
				<a href="${deleteLink}"
					onclick="if(!(confirm('Are you sure you want to delete this student?')))return false">Delete</a>
				</td>
			</tr>

		</c:forEach>

	</table>

</body>

</html>
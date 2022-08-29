<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update User</title>
</head>
<body>
<h1>UPDATE USER</h1>
<form action="userController" method="POST">

<input type="hidden" name="command" value="UPDATE"/>

<input type="hidden" name="studentId" value="${THE_STUDENT.id}"/>

FirstName:<input type="text" name="fname" placeholder="Enter First Name" value="${THE_STUDENT.firstName}" required ><br/>
lastName:<input type="text"name="lname" placeholder="Enter Last Name" value="${THE_STUDENT.lastName}" required><br/>
email:<input type="email" name="email" placeholder ="Enter email" value="${THE_STUDENT.email}" required><br/>

<input type="submit" value="ADD">

</form>
<a href="userController">Back to List</a>

</body>
</html>
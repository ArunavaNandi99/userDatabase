<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
</head>
<body>
<h1>ADD USER</h1>
<form action="userController" method="POST">
<input type="hidden" name="command" value="ADD"/>
FirstName:<input type="text" name="fname" placeholder="Enter First Name" required><br/>
lastName:<input type="text"name="lname" placeholder="Enter Last Name" required><br/>
email:<input type="email" name="email" placeholder ="Enter email" required><br/>
<input type="submit" value="ADD">

</form>
<a href="userController">Back to List</a>

</body>
</html>
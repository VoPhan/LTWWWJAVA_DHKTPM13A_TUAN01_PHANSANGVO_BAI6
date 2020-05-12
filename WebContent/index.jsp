<%@page import="entities.Person"%>
<%@page import="java.util.List"%>
<%@page import="daos.PersonDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bai6</title>

</head>
<body>
	<%
		PersonDAO personDAO = new PersonDAO();
		List<Person> persons = personDAO.getPeople();
	%>
	<span>${message}</span>
	<form action="AddPerson" method="POST" name="addPerson">
		<table>
			<tr>
				<td>Name :</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Country :</td>
				<td><input type="text" name="country"></td>
			</tr>
		</table>
		<button type="submit">Add Person</button>
		<br>
	</form>
	</br>
	<table style="align-items: center; width: 50%">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Country</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="<%=persons%>" var="people">
			<tr>
				<td>
					<c:out value='${people.id}'></c:out>
				</td>
				<td>
					<c:out value='${people.name}'></c:out>
				</td>
				<td>
					<c:out value='${people.country}'></c:out>
				</td>
				<td>
					<form action="FormEditServlet" method="post">
						<input type="hidden" name="idPerson" value="${people.id}">
						<button type="submit">Edit</button>
					</form>
				</td>
				<td>
					<form action="DeletePerson" method="post" name = "deletePerson">
						<input type="hidden" name="idPerson" value="${people.id}">
						<button type="submit">Delete</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>
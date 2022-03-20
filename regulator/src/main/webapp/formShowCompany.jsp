<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/company" var="linkEditCompany"> </c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>New Company</title>
</head>
<body>

<c:import url="logoutFragment.jsp"></c:import>

<!--  its better to use the url taglib because the context of the application can change and then the action url will be wrong
Preferences > Properties > Web Project Settings -->
<form action="${linkEditCompany}" method="POST">
	</br>
	Name: <input type="text" name="name" value="${company.name}" />
	</br></br>
	Creation Date: <input type="text" name="date"  value="<fmt:formatDate value="${company.creationDate}" pattern="dd/MM/yyyy"/>" />
	</br></br>
	<input type="hidden" name="id" value="${company.id}" />
	<input type="hidden" name="action" value="edit" />
	<input type="submit" value="Submit">
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/company" var="linkLoginServlet" />    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<c:import url="logoutFragment.jsp"></c:import>
	
	<form action="${linkLoginServlet }" method="post">
		<input type="hidden" name="command" value="Login"/>
		Nome: <input type="text" name="login"> 
		<br></br>
		Senha: <input type="password" name="password"/>
		<br></br>
		
		<input type="submit" value="Login"/>
	</form>

</body>
</html>
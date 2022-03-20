<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, br.com.regulator.model.CompanyModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/company?action=" var="linkCompany"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Companys List </title>
</head>

<body>

<c:import url="logoutFragment.jsp"></c:import>

<h2> Welcome, ${user.login}</h2>

<p> This is a list using scriplets. Not a good practice </p>
	<ul>
		<%
			List<CompanyModel> listCompany = (List<CompanyModel>)request.getAttribute("companys");
			for (CompanyModel company : listCompany) {
		%>
			<li> <%=  company.getName()  %> </li>
		<%
			}
		%>
		</ul>
		
		<p> Now using Expression Language </p>
		<p> Requires: JSTL lib (Java Standard Taglib) </p>
		<ul>
			<c:forEach items="${companys}" var="company">
				<li>
					${company.name}, since: <fmt:formatDate value="${company.creationDate}" pattern="dd/MM/yyyy" />; 
					<a href="${linkCompany}show&id=${company.id}">Edit</a>
					<a href="${linkCompany}remove&id=${company.id}">Remove</a>
				</li>
			</c:forEach>
		</ul>
		
</body>
</html>
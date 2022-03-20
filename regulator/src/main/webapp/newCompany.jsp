<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String fixedCompanyName = "CasareDev";
	String companyName = (String)request.getAttribute("companyName"); 
	System.out.println(fixedCompanyName);
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Company´s Info</title>
</head>
<body>
	
	<c:if test="${not empty companyName }">
		<p> Using Expression Language to show the new Company: ${companyName} </p>
	</c:if>
	
	<!-- equals if/else for JSP -->
	<c:choose> 
	    <c:when test="${not empty companyName}">
			<h1> Welcome to, <% out.println(companyName); %> </h1>
			<p> Another way to print a variable is using "<&nbsp%&nbsp= VAR %>" EX: <%= fixedCompanyName %>
	    </c:when>    
	    <c:otherwise>
	      	<p> No Company was added, please use POST methods to do it. </p>
	    </c:otherwise>
	</c:choose>
	
	
</body>
</html>
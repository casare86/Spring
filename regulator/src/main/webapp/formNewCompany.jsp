<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/company" var="linkNewCompany"> </c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>New Company</title>
</head>
<body>

<!--  its better to use the url taglib because the context of the application can change and then the action url will be wrong
Preferences > Properties > Web Project Settings -->
<form action="${linkNewCompany}" method="POST">
	</br>
	Name: <input type="text" name="name" />
	</br></br>
	Creation Date: <input type="text" name="date" />
	<input type="hidden" name="action" value="add" />
	</br></br>
	<input type="submit" value="Submit">
</form>
</body>
</html>
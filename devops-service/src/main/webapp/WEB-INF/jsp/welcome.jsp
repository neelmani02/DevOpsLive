<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<body>

	
	<div >

		<div >
			<h1>DevOps Live</h1>
			<h2>Message: <c:out value="${message}"></c:out></h2>
		</div>
		<div>
		<table border="2" width="70%" cellpadding="2">
	<tr>
	<th>Id</th>
	<th>Name</th>
	</tr>
	
    <c:forEach var="user" items="${list}"> 
    <tr>
    <td>${user.emailAddress}</td>
    <td>${user.displayName}</td>
    </tr>
    </c:forEach>
    </table>
    </div>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>